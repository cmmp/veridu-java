package com.veridu.endpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.veridu.exceptions.APIError;
import com.veridu.exceptions.EmptyResponse;
import com.veridu.exceptions.EmptySession;
import com.veridu.exceptions.EmptyUsername;
import com.veridu.exceptions.InvalidFormat;
import com.veridu.exceptions.InvalidResponse;
import com.veridu.exceptions.InvalidUsername;
import com.veridu.exceptions.NonceMismatch;
import com.veridu.exceptions.RequestFailed;
import com.veridu.exceptions.SignatureFailed;
import com.veridu.storage.Storage;

/**
 * Clone Resource
 *
 * @see <a href="https://veridu.com/wiki/Clone_Resource"> Wiki/Clone_Resource
 *      </a>
 * @version 1.0
 */
public class Clone extends AbstractEndpoint {

    public Clone(String key, String secret, String version, Storage storage) {
        super(key, secret, version, storage);
    }

    /**
     * Retrieves details about user's clones
     *
     * @return Details in JSON format
     *
     * @throws EmptySession
     *             Exception
     * @throws InvalidUsername
     *             Exception
     * @throws SignatureFailed
     *             Exception
     * @throws NonceMismatch
     *             Exception
     * @throws EmptyResponse
     *             Exception
     * @throws InvalidFormat
     *             Exception
     * @throws InvalidResponse
     *             Exception
     * @throws APIError
     *             Exception
     * @throws RequestFailed
     *             Exception
     * @throws EmptyUsername
     *             Exception
     * @throws ParseException
     *
     * @see <a href=
     *      "https://veridu.com/wiki/Clone_Resource#How_to_retrieve_details_about_users_clones">
     *      How to retrieve details about user's clones</a>
     */
    public JSONObject details() throws EmptySession, EmptyUsername, EmptyResponse, InvalidFormat, InvalidResponse,
            APIError, RequestFailed, SignatureFailed, NonceMismatch, InvalidUsername, ParseException {
        if (this.storage.isUsernameEmpty())
            throw new EmptyUsername();

        return details(this.storage.getUsername());
    }

    /**
     * Retrieves details about user's clones
     *
     * @param username
     *            String username
     *
     * @return Details in JSON format
     *
     * @throws EmptySession
     *             Exception
     * @throws InvalidUsername
     *             Exception
     * @throws SignatureFailed
     *             Exception
     * @throws NonceMismatch
     *             Exception
     * @throws EmptyResponse
     *             Exception
     * @throws InvalidFormat
     *             Exception
     * @throws InvalidResponse
     *             Exception
     * @throws APIError
     *             Exception
     * @throws RequestFailed
     *             Exception
     * @throws ParseException
     *
     * @see <a href=
     *      "https://veridu.com/wiki/Clone_Resource#How_to_retrieve_details_about_users_clones">
     *      How to retrieve details about user's clones</a>
     */
    public JSONObject details(String username) throws EmptySession, InvalidUsername, SignatureFailed, NonceMismatch,
            EmptyResponse, InvalidFormat, InvalidResponse, APIError, RequestFailed, ParseException {
        if (this.storage.isSessionEmpty())
            throw new EmptySession();

        if (!AbstractEndpoint.validateUsername(username))
            throw new InvalidUsername();

        JSONObject json = this.signedFetch("GET", String.format("clone/%s", username));

        return json;
    }

}
