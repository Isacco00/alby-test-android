package albytest_service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import albytest_bean.TestBean;
import albytest_service.interfaces.VolleyOnEventListener;

public class RestServiceTest {

    private Context context;
    private VolleyOnEventListener volleyOnEventListener;

    public RestServiceTest(Context context, VolleyOnEventListener volleyOnEventListener){
        this.context = context;
        this.volleyOnEventListener = volleyOnEventListener;
    }

    public Context getContext() {
        return context;
    }

    public void getPagedHookRelease(String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArrayRequest = new JSONArray(response);
                volleyOnEventListener.onResponseGetEntity(jsonArrayRequest.getJSONObject(0).getString("nome oggetto json"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            volleyOnEventListener.onResponseGetEntity("errore");
        }){
            /*@Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("tokenVersione",String.valueOf(0));
                data.put("versione","1.1");
                return data;
            }*/
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(request);
    }

    public void saveEntity(String url, TestBean entity) {
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                volleyOnEventListener.onResponseSaveEntity(jsonObject.getString("message.error.agganciosgancio"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> volleyOnEventListener.onResponseSaveEntity(error.getMessage())){

            /*
             Parametri della richiesta

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("tokenAggancioSgancio",String.valueOf(0));
                data.put("tokenUtente",String.valueOf(1));
                data.put("tokenVeicolo",entity.getVehicleBean().getTargaVeicolo());
                data.put("tokenFotoScattata",entity.getPictureTakenBean().getImage());
                data.put("tokenAudioVocale",String.valueOf(1));
                data.put("tipologiaAzione", entity.getActionType());
                data.put("tokenPosizioneGps",entity.getGpsPositionBean().getLuogo()); //String.valueOf(entity.getGpsPositionBean().getTokenGpsPosition())
                return data;
            }

             */
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(request);
    }


}
