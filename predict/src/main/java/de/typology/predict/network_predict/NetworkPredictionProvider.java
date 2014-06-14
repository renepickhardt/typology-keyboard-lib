package de.typology.predict.network_predict;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import de.typology.predict.PredictionProvider;
import de.typology.predict.model.Prediction;
import de.typology.predict.model.PredictionContext;

/**
 * Created by till on 5/23/14.
 */
public class NetworkPredictionProvider implements PredictionProvider{

    private static final String TAG = "NetworkPredictionProvider";

    private static final int FETCH_AMOUNT = 7;
    private static final int PREFIX_LENGTH = 2;
//    private static final String IP_ADDRESS = "192.168.1.11:8080";
//    private static final String REQUEST_ADDRESS =
//            "http://" + IP_ADDRESS + "/autocompleteServer-0.0.1-SNAPSHOT/suggest?term=%s&numItems=%d";
    private static final String REQUEST_ADDRESS_PREFIX = "http://";
    private static final String REQUEST_ADDRESS_POSTFIX =
            "/autocompleteServer-0.0.1-SNAPSHOT/suggest?term=%s&numItems=%d";
    private static final String URL_WORD_SEPARATOR = " ";
private static final String ENCODING = "UTF-8";

    private String mIpAddress;

    public NetworkPredictionProvider(String ipAddress) {
        mIpAddress = ipAddress;
    }

    @Override
    public void start() {
        Log.i(TAG, "started");
    }

    @Override
    public void end() {

    }

    public void setIpAddress(final String ipAddress) {
        mIpAddress = ipAddress;
    }

    @Override
    public List<Prediction> getPredictions(final PredictionContext context) {
        final String lookupPrefix = createLookupPrefix(context);
        final HttpResponse response = getServerPredictions(lookupPrefix);

//        Log.i(TAG, "received response: " + response);

        final String currentWordPrefix = context.getWordAt(context.getNumberOfWords() - 1);
        List<Prediction> predictions = predictionsFromResponse(response, currentWordPrefix, lookupPrefix);
        if (predictions.size() == 0)
            predictions.add(new Prediction(currentWordPrefix, 10));
        return predictions;
    }

    private static String createLookupPrefix(PredictionContext context) {
        final StringBuilder prefix = new StringBuilder();
        final int contextSize = context.getNumberOfWords();
        for (int i = (contextSize > PREFIX_LENGTH) ? contextSize - PREFIX_LENGTH : 0;
             i < contextSize; i++) {
            prefix.append(context.getWordAt(i) + URL_WORD_SEPARATOR);
        }
        if (prefix.length() > 0)
            prefix.deleteCharAt(prefix.length() - 1);
        return prefix.toString();
    }

    private HttpResponse getServerPredictions(String prefix) {
        HttpResponse response = null;
        try {
            final HttpClient client = new DefaultHttpClient();
            final HttpGet request = new HttpGet();

//            Log.i(TAG, "the prefix is: " + prefix);

            final String url = String.format(REQUEST_ADDRESS_PREFIX + mIpAddress +
                    REQUEST_ADDRESS_POSTFIX, URLEncoder.encode(prefix, ENCODING), FETCH_AMOUNT);
            request.setURI(new URI(url));
//            request.setURI(new URI("http://localhost:8080/autocompleteServer-0.0.1-SNAPSHOT/suggest?term=foo b&numItems=5&index=generalIndex"));
            
//            Log.i(TAG, "making request: " + request.getURI());

            response = client.execute(request);
            Log.i(TAG, "got response");
        } catch (URISyntaxException e) {
            //TODO: better exception handling
            Log.e(TAG, "error getting predictions from server: " + e.getMessage());
        } catch (IOException e) {
            //TODO: better exception handling
            Log.e(TAG, "error getting predictions from server: " + e.getMessage());
        }

        return response;
    }

    private static List<Prediction> predictionsFromResponse(HttpResponse response,
                                                            final String currentWordPrefix,
                                                            final String completePrefix) {
        final List<Prediction> predictions = new ArrayList<Prediction>();

        if (response == null)
            return predictions;

        Reader reader = null;
        try {
            final InputStream responseContent = response.getEntity().getContent();
            reader = new InputStreamReader(responseContent);

            final Gson gson = new Gson();
            final PredictionResponse convertedResponse =
                    gson.fromJson(reader, PredictionResponse.class);

            final int startCopyIndex = getStartingPrefixLength(completePrefix, currentWordPrefix);
            int score = convertedResponse.suggestionList.length;
            predictions.add(new Prediction(currentWordPrefix, score + 1));

            for (PredictionResponse.Suggestion sugg : convertedResponse.suggestionList) {
                final String suggText = sugg.suggestion.substring(startCopyIndex);
                if (!suggText.equals(currentWordPrefix))
                    predictions.add(new Prediction(suggText, score--));
            }

        } catch (IOException e) {
            Log.e(TAG, "Error converting predictions: " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing reader: " + e.getMessage());
            }

        }

//        Log.i(TAG, "got predictions: " + predictions);
        return predictions;
    }

    private static int getStartingPrefixLength(final String completePrefix,
                                                final String currentWordPrefix) {
        if (completePrefix.length() < currentWordPrefix.length()) {
            Log.e(TAG, "The complete prefix is shorter than a part of it, there's something wrong here: "
                + completePrefix + ", just the word: " + currentWordPrefix);
            return 0;
        }
        return completePrefix.length() - currentWordPrefix.length();
    }

    private class PredictionResponse {

        private class Suggestion {

            @SerializedName("suggestion")
            public String suggestion;
        }

        @SerializedName("suggestionList")
        public Suggestion[] suggestionList;

    }

}
