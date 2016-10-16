package com.me.harris.androidanimations._11_Loader;

import android.content.Intent;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityLoaderBinding;

import java.util.List;

/**
 * Created by Fermi on 2016/10/2.
 */

public class LoaderActivity extends AppCompatActivity {
    ActivityLoaderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader);
        setSupportActionBar(binding.toolbar);
        // Create the ListFragment and add it as our sole content.
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.frameLayout) == null) {
            AppListFragment list = new AppListFragment();
            fm.beginTransaction().add(R.id.frameLayout, list).commit();
        }
    }

    /**
     * This ListFragment displays a list of all installed applications on the
     * device as its sole content. It uses an {@link AppListLoader} to load
     * its data and the LoaderManager to manage the loader across the activity
     * and fragment life cycles.
     */
    public static class AppListFragment extends ListFragment implements
            LoaderManager.LoaderCallbacks<List<AppEntry>> {
        private static final String TAG = "ADP_AppListFragment";
        private static final boolean DEBUG = true;

        // We use a custom ArrayAdapter to bind application info to the ListView.
        private AppListAdapter mAdapter;

        // The Loader's id (this id is specific to the ListFragment's LoaderManager)
        private static final int LOADER_ID = 1;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setHasOptionsMenu(true);

            mAdapter = new AppListAdapter(getActivity());
            setEmptyText("No applications");
            setListAdapter(mAdapter);
            setListShown(false);

            if (DEBUG) {
                Log.i(TAG, "+++ Calling initLoader()! +++");
                if (getLoaderManager().getLoader(LOADER_ID) == null) {
                    Log.i(TAG, "+++ Initializing the new Loader... +++");
                } else {
                    Log.i(TAG, "+++ Reconnecting with existing Loader (id '1')... +++");
                }
            }

            // Initialize a Loader with id '1'. If the Loader with this id already
            // exists, then the LoaderManager will reuse the existing Loader.
            getLoaderManager().initLoader(LOADER_ID, null, this);
        }

        /**********************/
        /** LOADER CALLBACKS **/
        /**********************/

        @Override
        public android.support.v4.content.Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
            if (DEBUG) Log.i(TAG, "+++ onCreateLoader() called! +++");
            return new AppListLoader(getActivity());
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<List<AppEntry>> loader, List<AppEntry> data) {
            if (DEBUG) Log.i(TAG, "+++ onLoadFinished() called! +++");
            mAdapter.setData(data);

            if (isResumed()) {
                setListShown(true);
            } else {
                setListShownNoAnimation(true);
            }
        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<List<AppEntry>> loader) {
            if (DEBUG) Log.i(TAG, "+++ onLoadReset() called! +++");
            mAdapter.setData(null);
        }



        /**********************/
        /** CONFIGURE LOCALE **/
        /**********************/

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.activity_loader_main, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_configure_locale:
                    configureLocale();
                    return true;
            }
            return false;
        }

        /**
         * Notifies the Loader that a configuration change has has occurred (i.e. by
         * calling {@link Loader#onContentChanged()}).
         *
         * This feature was added so that it would be easy to see the sequence of
         * events that occurs when a content change is detected. Connect your
         * device via USB and analyze the logcat to see the sequence of methods that
         * are called as a result!
         */
        private void configureLocale() {
            android.support.v4.content.Loader<Object> loader = getLoaderManager().getLoader(LOADER_ID);
            if (loader != null) {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            }
        }
    }
}
