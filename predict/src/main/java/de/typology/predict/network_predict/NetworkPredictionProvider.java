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

    private static final int FETCH_AMOUNT = 5;
    private static final String IP_ADDRESS = "192.168.1.11:8080";
    private static final String REQUEST_ADDRESS =
            "http://" + IP_ADDRESS + "/autocompleteServer-0.0.1-SNAPSHOT/suggest?term=%s&numItems=%d";
//private static final String REQUEST_ADDRESS =
//        "http://google.com/suggest?term=%s&numItems=%d";

    private final HttpClient mClient;

    public NetworkPredictionProvider() {
        mClient = new DefaultHttpClient();
    }

    @Override
    public void start() {
        Log.i(TAG, "started");
    }

    @Override
    public void end() {

    }

//    @Override
//    public List<Prediction> getPredictions(PredictionContext context) {
//        throw new NullPointerException();
////        return null;
//    }

    @Override
    public List<Prediction> getPredictions(final PredictionContext context) {
        Log.i(TAG, "getting predictions");
//        throw new NullPointerException();
//        return null;
        final HttpResponse response = getServerPredictions(createLookupPrefix(context));
        Log.i(TAG, "received response: " + response);
        return predictionsFromResponse(response, context.getWordAt(context.getNumberOfWords() - 1));
    }

    private static String createLookupPrefix(PredictionContext context) {
        final int prefixLength = 2;
        final StringBuilder prefix = new StringBuilder();
        final int contextSize = context.getNumberOfWords();
        for (int i = (contextSize > prefixLength) ? contextSize - prefixLength : 0;
             i < contextSize; i++) {
            prefix.append(context.getWordAt(i) + " ");
        }
        if (prefix.length() > 0)
            prefix.deleteCharAt(prefix.length() - 1);
        return prefix.toString();
    }

    private HttpResponse getServerPredictions(String prefix) {
        HttpResponse response = null;
        try {
            final HttpGet request = new HttpGet();
            request.setURI(new URI(String.format(REQUEST_ADDRESS, prefix, FETCH_AMOUNT)));
            Log.d(TAG, "making request: " + request.getURI());
            response = mClient.execute(request);
            Log.d(TAG, "got response");
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
                                                            String defaultPrediction) {
        final List<Prediction> predictions = new ArrayList<Prediction>();

        predictions.add(new Prediction(defaultPrediction, 10));

        if (response == null)
            return predictions;

        Reader reader = null;
        try {
            final InputStream responseContent = response.getEntity().getContent();
            reader = new InputStreamReader(responseContent);

            final Gson gson = new Gson();
            final PredictionResponse convertedResponse =
                    gson.fromJson(reader, PredictionResponse.class);

            int score = convertedResponse.suggestionList.length;
            for (PredictionResponse.Suggestion sugg : convertedResponse.suggestionList) {
                predictions.add(new Prediction(sugg.suggestion, score--));
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

        Log.i(TAG, "got predictions: " + predictions);
        return predictions;
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
