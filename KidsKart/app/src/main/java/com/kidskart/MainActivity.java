package com.kidskart;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kidskart.application.MyVolley;
import com.kidskart.callbacks.SwitchFragmentsCallback;
import com.kidskart.fragment.CategoryListFragment;
import com.kidskart.util.Constants;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements SwitchFragmentsCallback{

    private static final String TAG = "MainActivity";

    public static final int FRAG_CATEGORY_LIST = 1;
    public static final int FRAG_SUBCATEGORY_LIST = 2;
    public static final int FRAG_PRODUCT_DETAIL = 3;
    public static final int FRAG_BITCH_CORNER = 4;
    public static final int FRAG_PROFILE = 5;
    public static final int FRAG_MORE = 6;
    public static final int FRAG_BITCH_LOGIN = 7;
    public static final int FRAG_BITCH_RULES = 8;

    private static int activeFragment = 0;

    Fragment currentFragmentInstance;
    Menu menu;
    ProgressDialog pDialog;
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //WebView webViewDashboard;
    String cartCount = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webViewDashboard = (WebView) findViewById(R.id.webViewDashboard);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_menu_black_24dp,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        )  {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);

        mDrawerLayout.setDrawerListener(mDrawerToggle);


            pDialog = new ProgressDialog(MainActivity.this);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("KidsKart");

        /*Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.rgb()), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);*/

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setLogo(R.drawable.logo);

        getActionBar().setDisplayUseLogoEnabled(false);

        //actionBar.setDisplayUseLogoEnabled(true);
        //getActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.logo);

        RequestQueue queue = MyVolley.getRequestQueue();
        StringRequest reqGetHomeData = new StringRequest(Request.Method.GET, Constants.GET_DASHBOARDHTML_CODE_URL,createMyReqSuccessListener(),
                createMyReqErrorListener());
        queue.add(reqGetHomeData);

        pDialog.setMessage("Loading ...");
        pDialog.show();
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //setTvResultText(response);
                Log.d(TAG,"Response = "+response);
                if(pDialog != null && pDialog.isShowing()){
                    pDialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String url = jsonObject.optString("home_url");

                    //initWebview(url);
                    CategoryListFragment fragment = new CategoryListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url",url);
                    fragment.setArguments(bundle);
                    switchFragmentAddToBackStack(fragment, FRAG_CATEGORY_LIST, null, true);
                    cartCount = jsonObject.optString("cart_count");
                    updateCartCount();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void updateCartCount(){
        if(menu != null){
            View count = menu.findItem(R.id.actionCart).getActionView();
            TextView menu_badge = (TextView) count.findViewById(R.id.menu_badge);
            menu_badge.setText(cartCount);
        }
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTvResult.setText(error.getMessage());
                Toast.makeText(MainActivity.this, getString(R.string.volley_error),Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;

        /*View count = menu.findItem(R.id.actionCart).getActionView();
        TextView menu_badge = (TextView) count.findViewById(R.id.menu_badge);
        menu_badge.setText("15");*/
        //ActionItemBadge.update(this, menu.findItem(R.id.actionCart), FontAwesome.Icon.faw_android, ActionItemBadge.BadgeStyles.DARK_GREY, badgeCount);
        //new ActionItemBadgeAdder().act(this).menu(menu).title("").itemDetails(0, 123, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).add(ActionItemBadge.BadgeStyles.GREY_LARGE, 1);
        return true;
    }

    /*private void initWebview(String urlToLoad){
        WebSettings settings = webViewDashboard.getSettings();
        settings.setJavaScriptEnabled(true);
        //webViewDashboard.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webViewDashboard.setScrollbarFadingEnabled(true);


        webViewDashboard.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click..." + url);
                //return super.shouldOverrideUrlLoading(view, url);
                startActivity(new Intent(MainActivity.this, SubCategoryListActivity.class));
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Page Load Complete..." + url);
                startActivity(new Intent(MainActivity.this, SubCategoryListActivity.class));
                super.onPageFinished(view, url);
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e(TAG, "Error: " + error.toString());
                Toast.makeText(MainActivity.this, getString(R.string.webview_error), Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, request, error);
            }
        });

        webViewDashboard.loadUrl(urlToLoad);
    }*/

    @Override
    public void switchFragment(Fragment fragment, int fragID, Object extras) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        activeFragment = fragID;
        currentFragmentInstance = fragment;
        for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++){
            getSupportFragmentManager().popBackStack();
        }
        transaction.replace(R.id.content_frame, fragment,String.valueOf(fragID)).addToBackStack(String.valueOf(fragID)).commit();
        //updateButtonBG();
        //prepareHeader();
    }

    @Override
    public void switchFragmentAddToBackStack(Fragment fragment, int fragID, Object extras, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        activeFragment = fragID;

        currentFragmentInstance = fragment;
        if(addToBackStack){
            transaction.replace(R.id.content_frame, fragment,String.valueOf(fragID)).addToBackStack(String.valueOf(fragID)).commit();
        }else{
            transaction.replace(R.id.content_frame, fragment).commit();
        }
        //updateButtonBG();
        //prepareHeader();
    }

    @Override
    public void popBackStack(boolean popBackStack) {

        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStackImmediate();
        }

    }

    private void findCurrentlyVisibleFragment(){
        FragmentManager.BackStackEntry backEntry=getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount()-1);
        for(int i = 0 ; i < getSupportFragmentManager().getBackStackEntryCount(); i ++){
            Log.d(TAG,"Backstack fragment at position "+i+ " is "+getSupportFragmentManager().getBackStackEntryAt(i).getName());
        }
        String str=backEntry.getName();
        Log.d(TAG,"Found Fragment id "+str);
        Fragment fragment=getSupportFragmentManager().findFragmentByTag(str);
        if(fragment != null){
            activeFragment = Integer.parseInt(str);
            currentFragmentInstance = fragment;

            //prepareHeader();
            //updateButtonBG();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        this.menu = menu;
        updateCartCount();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        this.menu = menu;
        updateCartCount();
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.actionCart).setVisible(!drawerOpen);
        menu.findItem(R.id.actionSearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);

    }
}
