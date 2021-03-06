package de.typology.predict;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Handler;
import android.widget.Toast;

import java.lang.CharSequence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.typology.predict.PredictionConfig.PredictionConfigChangeListener;
import de.typology.predict.model.Prediction;
import de.typology.predict.model.PredictionContext;
import de.typology.predict.network_predict.IPAddressManager;
import de.typology.predict.network_predict.NetworkPredictionProvider;

/**
 * The class for managing the prediction computation.
 * 
 * @author till
 * 
 */
public final class Predict implements PredictionConfigChangeListener {

    private static final String TAG = "Predict";

    private static final String IP_CONFIG_HOTWORD = "wombat";

    //TODO: get rid of this later because it makes the code dependent of Android
    private final android.content.Context mContext;

    private final PredictionContextComposer mComposer;
    private final PredictionRequestHandler mHandler;

	public Predict(final Context context) {
        this.mContext = context;
        mComposer = new PredictionContextComposer();
        mHandler = new PredictionRequestHandler(IPAddressManager.getIpAddress(mContext));
	}

	/**
	 * @return The instance of the PredictionContextComposer used by this
	 *         PredictionProvider
	 */
	public PredictionContextComposer getPredictionContextComposer() {
		return mComposer;
	}

	/**
	 * Loads the sources for prediction computation asynchronously.
	 */
	public void loadPredictionSources() {
	}

	public void closePredictionSources() {
	}

    public enum PredictionMode {
        /**
         * Predictions with a different prefix than the given
         * prefix are allowed.
         */
        CORRECT_CURRENT_WORD,
        /**
         * Predictions with a different prefix than the given
         * prefix are not allowed.
         */
        DO_NOT_CORRECT_CURRENT_WORD
        // batch input,...
    }

//	this method probably makes sense but with respect to our usecase
//	the PredictionContextComposer should be used
//	re-add this later
	/**
	* Computes predictions given the current word and its
	* predecessors
	*
	* @param currentWord The word that is currently typed
	* @param previousWords The predecessor words of the current word
	* @param correctCurrentWord Whether the the current word should be
	* corrected
	* @param callback the callback to call when the computation is done
	* @return The id of this prediction querry
	*/
	public long getPredictions(final String[] words,
            final PredictionMode mode, final OnPredictionsComputedCallback callback) {

//        List<Prediction> predictions = new ArrayList<Prediction>();
//        if (words.size() > 0) {
////            Prediction typedWord = new Prediction(words.get(0), words.size());
//            //add it twice: the first word is the typed word and the second one the most likely word
////            predictions.add(typedWord);
//
//            //TODO: change iteration direction, this is only due to hacked implementation
//            final int size = words.size();
//            for (int i = 0; i < size; i++) {
//                predictions.add(new Prediction(words.get(i), i));
//            }
//        }
//        callback.onPredictionsComputed(predictions, 0);
//        return 0;

        if (words.length > 0 && words[words.length - 1].
                toLowerCase().equals(IP_CONFIG_HOTWORD)) {

            final String ipAddress = IPAddressManager.getIpAddress(mContext);
            ((NetworkPredictionProvider) mHandler.getProvider()).setIpAddress(ipAddress);

            final Toast toast = Toast.makeText(mContext, "Changed the server address to " +
                    ipAddress, Toast.LENGTH_LONG);
            toast.show();
//            Log.i(TAG, "found hotword");
        }

//        final CharSequence[] prevWords = words.toArray(new CharSequence[words.size()]);
        final CharSequence[] prevWords = words;
        final PredictionContext context = new PredictionContext(prevWords);

//        Log.i(TAG, "getting predictions for " + Arrays.toString(prevWords));

        return mHandler.getPredictions(context, mode, callback);

//        return 0;
	}

	/**
	 * Computes predictions using the current state of the
	 * PredictionContextComposer
	 * 
	 * @param mode
	 *            How the predictions are computed
	 * @param callback
	 *            The callback to call when the computation is done
	 * @return The id of this prediction querry
	 */
	public long getPredictions(final PredictionMode mode,
			final OnPredictionsComputedCallback callback) {

        List<Prediction> predictions = new ArrayList<Prediction>();

        String value = mComposer.getTypedWord().toString();
        Prediction typedWord = new Prediction(value, 200);
        //add it twice: the first word is the typed word and the second one the most likely word
        predictions.add(typedWord);
        predictions.add(typedWord);

        List<String> prevs = mComposer.getPrevWords();
        for (int i = prevs.size() - 1; i >= 0; i--) {
            predictions.add(new Prediction(prevs.get(i), i));
        }

        callback.onPredictionsComputed(predictions, 0);

        return 0;
	}

	//we are not learning yet, readd this later
//	/**
//	 * Reports that the current word (the word the cursor is currently in) was
//	 * commited to the editor
//	 */
//	// We use this to give feedback to a learning prediction source
//	// TODO: should this be here or in the PredictionContextComposer? If so,
//	// we would need a listener for this event
//	// RENE: the question is where is the onselct event fired? (the question
//	// really is how much android is in the interfaces?)
//	public void reportCurrentWordCommited() {
//	}

	@Override
	public void onPredictionConfigChanged() {
		// reload PredictionProviders here
	}
	
	// multiple things need to be done asynchronously:
	// -getting suggestions
	// -loading (and closing?) the PredictionProviders
	// -feeding back information to the PredictionProviders

//    private static final class PredictionHandler implements Handler.Callback {
//        private Handler mHandler;
//
//        @Override
//        public boolean handleMessage(Message message) {
//            return false;
//        }
//    }

    private final class PredictionRequestHandler {

        private static final int NUMBER_OF_THREADS = 1;

        private long mIdCounter;
        private final PredictionProvider mProvider;
        private final ExecutorService mExecutor;
        private final Handler mUiThreadHandler;

        private PredictionRequestHandler(String ipAddress) {
            mIdCounter = 0;
            mProvider = new NetworkPredictionProvider(ipAddress);
            mExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            mUiThreadHandler = new Handler(mContext.getMainLooper());
        }

        private long getPredictions(final PredictionContext context,
                PredictionMode mode, OnPredictionsComputedCallback callback) {
            final long id = mIdCounter++;
            final PredictionRequest request = new PredictionRequest(id, context, callback);
            mExecutor.submit(request);
            return id;
        }

        @Deprecated
        private PredictionProvider getProvider() {
            return mProvider;
        }

        private final class PredictionRequest implements Callable<List<Prediction>> {
            private final long mId;
            private final PredictionContext mContext;
            private final OnPredictionsComputedCallback mCallback;

            private PredictionRequest(final long id, final PredictionContext context,
                                      final OnPredictionsComputedCallback callback) {
                this.mId = id;
                this.mContext = context;
                this.mCallback = callback;
            }

            @Override
            public List<Prediction> call() {
//                Log.i(TAG, "getting predictions (call)");
                final List<Prediction> predictions = mProvider.getPredictions(mContext);

                //TODO: find another way to get back to the calling thread that doesn't depend
                //on Android
                mUiThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onPredictionsComputed(predictions, mId);
                    }
                });
                return null;
            }

        }

    }

}
