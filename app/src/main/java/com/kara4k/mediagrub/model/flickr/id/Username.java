
package com.kara4k.mediagrub.model.flickr.id;

import com.google.gson.annotations.SerializedName;

public class Username {

    @SerializedName("_content")
    private String m_content;

    public String get_content() {
        return m_content;
    }

    public void set_content(String _content) {
        m_content = _content;
    }

}
