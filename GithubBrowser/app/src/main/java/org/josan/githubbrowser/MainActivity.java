package org.josan.githubbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<Profile> mProfileList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProfileRecyclerViewAdapter mProfileRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        mProfileRecyclerViewAdapter = new ProfileRecyclerViewAdapter(
                MainActivity.this,
                new ArrayList<Profile>()
        );
        mRecyclerView.setAdapter(mProfileRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(MainActivity.this, "Normal Tap", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(
                                MainActivity.this,
                                ViewPhotoDetailsActivity.class
                        );

                        intent.putExtra(
                                        PROFILE_TRANSFER,
                                mProfileRecyclerViewAdapter.getProfile(position)
                        );
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Long Tap", Toast.LENGTH_SHORT).show();
                    }
                }
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );
        String query = getSavedPreferenceData(PROFILE_QUERY);
        if (query.length() > 0) {
            ProcessProfiles processProfiles =
                    new ProcessProfiles(query, true);
            processProfiles.execute();
        }
    }

    private String getSavedPreferenceData(String profileQuery) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );

        return sharedPreferences.getString(profileQuery, "Raojayou");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProcessProfiles extends GetGithubJsonData {

        public ProcessProfiles(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        @Override
        public void execute() {
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();

        }

        public class ProcessData extends DownloadJsonData {

            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                mProfileRecyclerViewAdapter.loadNewData(getProfiles());
            }
        }
    }
}
