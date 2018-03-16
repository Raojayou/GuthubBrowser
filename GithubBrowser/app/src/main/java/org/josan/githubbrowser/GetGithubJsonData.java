package org.josan.githubbrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetGithubJsonData extends GetRawData {

    private static final String LOG_TAG = GetGithubJsonData.class.getSimpleName();

    private List<Profile> mProfiles;
    private Uri mDestinationUri;

    public GetGithubrJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mProfiles = new ArrayList<>();
    }

    public List<Profile> getProfiles() {
        return mProfile;
    }

    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String GITHUB_BASE_API_URL = "https://api.github.com/users";
                .build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String PROFILE_LOGIN = "login";
        final String PROFILE_ID = "id";
        final String PROFILE_AVATAR_URL = "avatar_url";
        final String PROFILE_URL = "url";
        final String PROFILE_HTML_URL = "html_url";
        final String PROFILE_REPOS_URL = "repos_url";

        if (getDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {
            //JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = new JSONArray(getData());

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonProfile = itemsArray.getJSONObject(i);
                String login = jsonProfile.getString(PROFILE_LOGIN);
                String id = jsonProfile.getString(PROFILE_ID);
                String avatar_url = "https://api.github.com" + jsonProfile.getString(PROFILE_AVATAR_URL);
                String url = jsonProfile.getString(PROFILE_URL);
                String html_url = jsonProfile.getString(PROFILE_HTML_URL);
                String repos_url = jsonProfile.getString(PROFILE_REPOS_URL);

                Profile profile = new Profile(login, id, avatar_url, url, html_url, repos_url);
                mProfiles.add(profile);
            }

            for (Profile profile : mProfiles) {
                Log.d(LOG_TAG, "Perfil: " + profile.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute() {
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String[] par = {mDestinationUri.toString()};

            return super.doInBackground(par);
        }
    }
}