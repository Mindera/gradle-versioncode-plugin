package com.mindera.gradle.versioncode.utils

import groovy.json.JsonSlurper

/**
 *  Created by ricardovieira on 06/05/15.
 */
class VersionCodeService {

    private def static String CURL_CMD = "curl"
    private def static String APP_ID_KEY = "<app-id>"
    private def static String VERSION_CODE_METHOD_TPL = "versionCode/${APP_ID_KEY}"
    private def static String INCREMENT_VERSION_CODE_METHOD_TPL = "versionCode/${APP_ID_KEY}/next"

    private def String mEndpoint
    private def String mAppId
    private def boolean mEnabled

    public VersionCodeService(String serviceEndpoint, String appId, boolean enabled) {

        mEndpoint = serviceEndpoint
        mAppId = appId
        mEnabled = enabled
    }

    public VersionCodeService(String serviceEndpoint, String appId) {

        mEndpoint = serviceEndpoint
        mAppId = appId
        mEnabled = true
    }

    public int incrementVersionCode() {

        if (!mEnabled) {
            return 0
        }

        def url = "${mEndpoint}/${INCREMENT_VERSION_CODE_METHOD_TPL}".replaceAll(APP_ID_KEY, mAppId)
        def curlProcess = [CURL_CMD, url].execute()

        curlProcess.waitFor()
        def curlReturnCode = curlProcess.exitValue()

        if (curlReturnCode != 0) {
            throw new Exception("curl command not successfully executed: ${curlReturnCode}")
        }

        def jsonResponse = new JsonSlurper().parseText(curlProcess.text)
        return jsonResponse.VersionCode
    }

    public int currentVersionCode() {

        if (!mEnabled) {
            return 0
        }

        def url = "${mEndpoint}/${VERSION_CODE_METHOD_TPL}".replaceAll(APP_ID_KEY, mAppId)
        def curlProcess = [CURL_CMD, url].execute()

        curlProcess.waitFor()
        def curlReturnCode = curlProcess.exitValue()

        if (curlReturnCode != 0) {
            throw new Exception("curl command not successfully executed: ${curlReturnCode}")
        }

        def jsonResponse = new JsonSlurper().parseText(curlProcess.text)
        return jsonResponse.VersionCode
    }

    public String getServiceEndpoint() {
        return mEndpoint
    }

    public String getAppId() {
        return mAppId
    }
}
