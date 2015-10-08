package com.kidskart.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.kidskart.MainActivity;
import com.kidskart.R;
import com.kidskart.adapter.CategoryDetailAdapter;
import com.kidskart.application.MyVolley;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.data.SubCategoryData;
import com.kidskart.data.category.CategoryData;
import com.kidskart.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nilesh on 20/09/15.
 */
public class CategoryListFragment extends Fragment {

    private static final String TAG = "CategoryList";
    ArrayList<SubCategoryData> arrSubcategoryData = new ArrayList<>();

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SwitchFragmentsCallback switchFragmentsCallback;
    ImageView imgBack,imgFilter;
    JSONObject requestJson;
    //ArrayList<CategoryData> arrCategoryData;
    CategoryData categoryData;
    TextView txtCategoryCount,txtCategoryName;

    ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categorylist_fragment,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        imgFilter = (ImageView) view.findViewById(R.id.imgFilter);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragmentsCallback.popBackStack(true);
            }
        });

        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiltersFragment fragment = new FiltersFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("filters",categoryData.getFilters());
                fragment.setArguments(bundle);
                switchFragmentsCallback.switchFragmentAddToBackStack(fragment, MainActivity.FRAG_FILTER,null,true);
            }
        });

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Getting Products...");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        txtCategoryCount = (TextView) view.findViewById(R.id.txtCategoryCount);
        txtCategoryName = (TextView) view.findViewById(R.id.txtCategoryName);

        arrSubcategoryData.clear();
        /*for(int i = 0; i < 5; i ++){
            SubCategoryData data = new SubCategoryData();
            data.setTitle("Boys Mineral Red Sicillian Road Trip Graphic Item "+i);
            data.setDescription(getString(R.string.dummy_data_big));
            data.setPrice(i*i + 20);
            arrSubcategoryData.add(data);
        }*/

        Bundle bundle = getArguments();
        if (bundle != null) {
            String key = bundle.getString("key");
            String value = bundle.getString("value");
            requestJson = new JSONObject();
            try {
                requestJson.putOpt(key,value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG," RequestJSON = "+requestJson);
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.POST, Constants.GET_PRODUCT_DATA_URL, requestJson, categoryDataReqSuccessListener(),categoryDataReqErrorListener());

        /*StringRequest reqGetHomeData = new StringRequest(Request.Method.POST, Constants.GET_PRODUCT_DATA_URL,categoryDataReqSuccessListener(),
                categoryDataReqErrorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return super.getParams();
            }
        };*/
        jRequest.setShouldCache(false);
        queue.add(jRequest);
        pDialog.show();


        return view;
    }


    private Response.ErrorListener categoryDataReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTvResult.setText(error.getMessage());
                if(pDialog != null && pDialog.isShowing()){
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), getString(R.string.volley_error), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Response.Listener<JSONObject> categoryDataReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //setTvResultText(response);
                //Log.d(TAG, "Response = " + response);
                if(pDialog != null && pDialog.isShowing()){
                    pDialog.dismiss();
                }
                if (response != null) {
                    Log.d(TAG, " Result = "+response);
                    Gson gson = new Gson();
                    categoryData = gson.fromJson(response.toString(),CategoryData.class);
                    if (categoryData != null) {
                        Log.d(TAG,"Product Count "+categoryData.getTotalProductCount());
                        setInitialValues(categoryData);
                    }
                    mAdapter = new CategoryDetailAdapter(categoryData,getActivity(),switchFragmentsCallback);
                    mRecyclerView.setAdapter(mAdapter);
                    //mAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    private void setInitialValues(CategoryData data){
        txtCategoryCount.setText("("+data.getTotalProductCount()+" items)");
        txtCategoryName.setText(data.getCategory_name());
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        switchFragmentsCallback = (SwitchFragmentsCallback) getActivity();
    }
}
