
package com.kara4k.mediagrub.model.inst.users;

import com.google.gson.annotations.SerializedName;

public class UsersResponse {

    @SerializedName("graphql")
    private Graphql mGraphql;

    public Graphql getGraphql() {
        return mGraphql;
    }

    public void setGraphql(Graphql graphql) {
        mGraphql = graphql;
    }

}
