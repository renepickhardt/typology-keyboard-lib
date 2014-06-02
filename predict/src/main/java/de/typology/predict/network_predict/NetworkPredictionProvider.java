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
    private static final String IP_ADDRESS = "91.50.53.80";
    private static final String REQUEST_ADDRESS =
            "http://10.0.2.2:8080/autocompleteServer-0.0.1-SNAPSHOT/suggest?term=%s&numItems=%d";
//private static final String REQUEST_ADDRESS =
//        "http://google.com/suggest?term=%s&numItems=%d";

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public List<Prediction> getPredictions(PredictionContext context) {
        Log.d(TAG, "getting predictions");
        final HttpResponse response = getServerPredictions(createLookupPrefix(context));
        Log.d(TAG, "received response: " + response);
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

    private static HttpResponse getServerPredictions(String prefix) {
        HttpResponse response = null;
        try {
            final HttpClient client = new DefaultHttpClient();
            final HttpGet request = new HttpGet();
            request.setURI(new URI(String.format(REQUEST_ADDRESS, prefix, FETCH_AMOUNT)));
            Log.d(TAG, "making request: " + request.getURI());
            response = client.execute(request);
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
        if (response == null)
            return predictions;

        try {
            final InputStream responseContent = response.getEntity().getContent();
            final Reader reader = new InputStreamReader(responseContent);

            final Gson gson = new Gson();
            final PredictionResponse convertedResponse =
                    gson.fromJson(reader, PredictionResponse.class);

            int score = convertedResponse.suggestionList.length;
            for (PredictionResponse.Suggestion sugg : convertedResponse.suggestionList) {
                predictions.add(new Prediction(sugg.suggestion, score--));
            }
            return predictions;

        } catch (IOException e) {
            Log.e(TAG, "Error converting predictions: " + e.getMessage());
        }
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
