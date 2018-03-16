package org.josan.githubbrowser;

import java.io.Serializable;


public class Profile implements Serializable {

    private static final long serialVersionUID = 4530162258339656778L;

    private String mLogin;
    private String mId;
    private String mAvatar_url;
    private String mUrl;
    private String mHtml_url;
    private String mRepos_url;


    public Profile(String mLogin, String mId, String mAvatar_url, String mUrl, String mHtml_url, String mUrl, String mRepos_url) {
        this.mLogin = mLogin;
        this.mId = mId;
        this.mAvatar_url = mAvatar_url;
        this.mUrl = mUrl;
        this.mHtml_url = mHtml_url;
        this.mRepos_url = mRepos_url;
    }

    public String getmLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmAvatar_url() {
        return mAvatar_url;
    }

    public void setmAvatar_url(String mAvatar_url) {
        this.mAvatar_url = mAvatar_url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmHtml_url() {
        return mHtml_url;
    }

    public void setmHtml_url(String mHtml_url) {
        this.mHtml_url = mHtml_url;
    }

    public String getmRepos_url() {
        return mRepos_url;
    }

    public void setmRepos_url(String mRepos_url) {
        this.mRepos_url = mRepos_url;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Profile{" +
                "mLogin='" + mLogin + '\'' +
                ", mId='" + mId + '\'' +
                ", mAvatar_url='" + mAvatar_url + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mHtml_url='" + mHtml_url + '\'' +
                ", mRepos_url='" + mRepos_url + '\'' +
                '}';
    }
}