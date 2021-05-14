package com.app.yoparkerassignment.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.yoparkerassignment.Commonuses.CommonAlertDialog;
import com.app.yoparkerassignment.Commonuses.PreferenceProvider;
import com.app.yoparkerassignment.Commonuses.TextClass;
import com.app.yoparkerassignment.LocationService.LocationAddress;
import com.app.yoparkerassignment.LocationService.LocationUpdatesService;
import com.app.yoparkerassignment.LocationService.Utils;
import com.app.yoparkerassignment.Models.DataItem;
import com.app.yoparkerassignment.Models.GetAllParkingResponse;
import com.app.yoparkerassignment.Models.LocationModel;
import com.app.yoparkerassignment.R;
import com.app.yoparkerassignment.activities.ParkingDetailActivity;
import com.app.yoparkerassignment.adapter.AutoSuggestAdapter;
import com.app.yoparkerassignment.adapter.HomeListRecyclerAdapter;
import com.app.yoparkerassignment.adapter.HomeRecyclerAdapter;
import com.app.yoparkerassignment.home.HomeActivity;
import com.app.yoparkerassignment.interfces.OnRecyclerItemClick;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback, OnRecyclerItemClick {

    private RecyclerView recyclerView, recyclerView1;
    private PreferenceProvider provider;
    private boolean isInternetConnection = true;
    private JSONObject userObject = null;
    private GoogleMap googleMap;
    private List<DataItem> items;
    private LatLngBounds.Builder builder;
    private CameraUpdate cu;
    private OnRecyclerItemClick itemClick, listitemClick;
    private Context context;
    private LocationModel locationModel;
    private HomeRecyclerAdapter adapter;
    private HomeListRecyclerAdapter ListAdapter;
    private AppCompatAutoCompleteTextView SearchEDT;
    private Handler handler;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private AutoSuggestAdapter autoSuggestAdapter;
    List<String> list, placeId;
    PlacesClient placesClient;
    private RelativeLayout RecyclerListLayout, CurrentLocationBTN;
    private ConstraintLayout MapLayout;
    private boolean isSearching;
    double latitude;
    double longitude;
    private MyReceiver myReceiver;
    public static boolean isCurrentLocationClick;
    public static int CURRENT_LOCATION = 0;
    public static int SEARCH_LOCATION = 1;
    private LinearLayoutManager linearLayoutManager;
    private AlertDialog alertDialog;
    private int width = 0;
    private int height = 10;
    private int ForLoopPosition;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, null, false);

        itemClick = this;
        listitemClick = this;
        myReceiver = new MyReceiver();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        provider = new PreferenceProvider(getActivity());
        String userObjStr = provider.GetLoginResponse();
        try {
            userObject = new JSONObject(userObjStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        locationModel = provider.getLocationDetail();
        SearchEDT = view.findViewById(R.id.SearchEDT);
        MapLayout = view.findViewById(R.id.MapLayout);
        CurrentLocationBTN = view.findViewById(R.id.CurrentLocationBTN);
        alertDialog = CommonAlertDialog.CreateDialog(getActivity(), getString(R.string.please_wait));
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        RecyclerListLayout = view.findViewById(R.id.RecyclerListLayout);
        linearLayoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int VisiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (VisiblePosition >= 0 && VisiblePosition != ForLoopPosition) {
                    ForLoopPosition = VisiblePosition;
                    GetDistance(ForLoopPosition);
                }
                //   Log.e("Position",""+linearLayoutManager.findFirstCompletelyVisibleItemPosition());
            }
        });
        recyclerView1.setLayoutManager(new LinearLayoutManager(requireActivity()));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        String apiKey = getString(R.string.API_KEY);
        if (!Places.isInitialized()) {
            Places.initialize(HomeActivity.homeActivity, apiKey);

        }
        placesClient = Places.createClient(HomeActivity.homeActivity);
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        autoSuggestAdapter = new AutoSuggestAdapter(HomeActivity.homeActivity,
                android.R.layout.simple_spinner_dropdown_item);

        SearchEDT.setThreshold(2);
        SearchEDT.setAdapter(autoSuggestAdapter);
        CurrentLocationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
                isSearching = false;
                locationModel.setLocationType(CURRENT_LOCATION);
                if (locationModel.getCur_latitude() == 0.0 && locationModel.getCur_longitude() == 0.0) {
                    isCurrentLocationClick = true;
                    HomeActivity.homeActivity.CheckPermission();
                } else {
                    isCurrentLocationClick = true;
                    // GetAllParking();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(locationModel.getCur_latitude(), locationModel.getCur_longitude(),
                            HomeActivity.homeActivity, new GeocoderHandler());
                }
            }
        });

        SearchEDT.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        locationModel.setLocationType(SEARCH_LOCATION);
                        hideKeyboard(HomeActivity.homeActivity);
                        GetLatLong(placeId.get(position));
                    }
                });
        SearchEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(SearchEDT.getText())) {
                        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                                // Call either setLocationBias() OR setLocationRestriction().
                                //.setLocationBias(bounds)
                                //.setLocationRestriction(bounds)
                                .setCountry("IN")//India
                                // .setTypeFilter(TypeFilter.CITIES)
                                .setSessionToken(token)
                                .setQuery(SearchEDT.getText().toString())
                                .build();
                        placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
                            StringBuilder mResult = new StringBuilder();
                            list = new ArrayList<>();
                            list.clear();
                            placeId = new ArrayList<>();
                            placeId.clear();
                            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {

                                mResult.append(" ").append(prediction.getFullText(null) + "\n");
                                list.add(String.valueOf(prediction.getFullText(null)));
                                placeId.add(prediction.getPlaceId());
                                //   Log.i(TAG, prediction.getPlaceId());

                                // Log.i(TAG, prediction.getPrimaryText(null).toString());
                                // Toast.makeText(AddParkingMapActivity.this, prediction.getPrimaryText(null) + "-" + prediction.getSecondaryText(null), Toast.LENGTH_SHORT).show();
                            }
                            autoSuggestAdapter.setData(list);
                            autoSuggestAdapter.notifyDataSetChanged();


                            // mSearchResult.setText(String.valueOf(mResult));
                        }).addOnFailureListener((exception) -> {
                            if (exception instanceof ApiException) {
                                ApiException apiException = (ApiException) exception;
                                // Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                            }
                        });

                    }
                }
                return false;
            }
        });
        SearchEDT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    hideKeyboard(HomeActivity.homeActivity);
                }
                return false;
            }
        });

        return view;
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locationModel.setCur_latitude(latitude);
                locationModel.setCur_longitude(longitude);
                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        HomeActivity.homeActivity, new GeocoderHandler());

                /*Toast.makeText(SelectLocationActivity.this, Utils.getLocationText(location),
                        Toast.LENGTH_SHORT).show();*/
            }
        }
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            String locationAddress, city = "", state = "", postal_code = "", country = "", address = "";
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();

                    city = bundle.getString("city");
                    state = bundle.getString("state");
                    postal_code = bundle.getString("postal_code");
                    country = bundle.getString("country");
                    address = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            locationModel.setCity(city);
            locationModel.setAddress(address);
            locationModel.setCountry(country);
            locationModel.setState(state);
            locationModel.setPostal_code(postal_code);
            provider.SaveLocationDetail(locationModel);
            if (isCurrentLocationClick) {
                GetAllParking();
            }
           /* if(alertDialog != null)
            {
                if(alertDialog.isShowing())
                {
                    alertDialog.dismiss();
                }
            }*/

            // tvAddress.setText(locationAddress);
        }
    }

    public void GetAllParking() {
        if (isInternetConnection) {
            //isCurrentLocationClick =false;
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            getParkingFromJson();

            SearchEDT.setText("", false);
        } else {
            Toast.makeText(getActivity(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        try {
            getParkingFromJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void GetDistance(int position) {

        String start = "" + locationModel.getLatitude() + "," + locationModel.getLongitude();
        String end = "" + items.get(position).getLatitude() + "," + items.get(position).getLongitude();
        String key = HomeActivity.homeActivity.getString(R.string.API_KEY);
        try {
            items.get(ForLoopPosition).setDistance("10 KM");
            adapter.notifyItemChanged(ForLoopPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getParkingFromJson() {
        if (googleMap != null) {
            Gson gson = new Gson();
            String jsonFileString = Utils.loadJSONFromAsset(getActivity(), "parkinglist.json");
            GetAllParkingResponse getAllParkingResponse = gson.fromJson(jsonFileString, GetAllParkingResponse.class);
            items = getAllParkingResponse.getData();

            TextClass.items = items;

            List<Marker> markers = new ArrayList<>();
            builder = new LatLngBounds.Builder();
            for (int i = 0; i < items.size(); i++) {
                ForLoopPosition = i;
                DataItem item = items.get(i);
                Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(item.getLatitude()), Double.parseDouble(item.getLongitude()))).icon(BitmapDescriptorFactory.fromResource(R.drawable.group86)).title(item.getName()));
                markers.add(marker);
                builder.include(marker.getPosition());
            }
            if (items.size() > 0) {
                ForLoopPosition = 0;
                GetDistance(ForLoopPosition);
                int padding = 100;
                LatLngBounds bounds = builder.build();
                cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                //googleMap.animateCamera(cu);
                isCurrentLocationClick = false;
                HomeActivity.homeActivity.SetTitle(locationModel.getCity());
            } else {
                if (!isSearching) {
                    googleMap.clear();
                    if (isCurrentLocationClick) {
                        isCurrentLocationClick = false;
                        HomeActivity.homeActivity.SetTitle(locationModel.getCity());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locationModel.getCur_latitude(), locationModel.getCur_longitude())));
                    } else {
                        if (locationModel.getLocationType() == SEARCH_LOCATION) {
                            HomeActivity.homeActivity.SetTitle(locationModel.getCity());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locationModel.getLatitude(), locationModel.getLongitude())));
                        } else {
                            HomeActivity.homeActivity.SetTitle(locationModel.getCity());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locationModel.getCur_latitude(), locationModel.getCur_longitude())));
                        }
                    }
                    //googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                } else {
                    HomeActivity.homeActivity.SetTitle(locationModel.getCity());
                    googleMap.clear();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
                    //googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                }
            }

            adapter = new HomeRecyclerAdapter(HomeActivity.homeActivity, true, items, width, itemClick);
            ListAdapter = new HomeListRecyclerAdapter(HomeActivity.homeActivity, false, items, width, new OnRecyclerItemClick() {
                @Override
                public void OnItemClick(int position) {
                    TextClass.item = items.get(position);
                    getActivity().startActivity(new Intent(getActivity(), ParkingDetailActivity.class));
                }
            });
            recyclerView.setAdapter(adapter);
            recyclerView1.setAdapter(ListAdapter);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void GetLatLong(String placeId) {
// Specify the fields to return.
        final List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME);

// Construct a request object, passing the place ID and fields array.
        final FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, placeFields);

        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();
            latitude = place.getLatLng().latitude;
            longitude = place.getLatLng().longitude;
            //locationModel.setCity(place.getName());
            locationModel.setLatitude(latitude);
            locationModel.setLongitude(longitude);
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    HomeActivity.homeActivity, new GeocoderHandler());
            isSearching = true;

        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                final ApiException apiException = (ApiException) exception;

                final int statusCode = apiException.getStatusCode();
                // TODO: Handle error with given status code.
            }
        });
    }


    public void setListMap(boolean isVisible) {
        if (isVisible) {
            MapLayout.setVisibility(View.VISIBLE);
            RecyclerListLayout.setVisibility(View.GONE);
        } else {
            MapLayout.setVisibility(View.INVISIBLE);
            RecyclerListLayout.setVisibility(View.VISIBLE);
        }

    }
}