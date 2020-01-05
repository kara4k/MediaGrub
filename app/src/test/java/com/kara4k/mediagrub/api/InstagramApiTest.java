package com.kara4k.mediagrub.api;

import com.kara4k.mediagrub.di.DaggerTestApiComponent;
import com.kara4k.mediagrub.di.modules.RetrofitModule;
import com.kara4k.mediagrub.model.inst.RequestObject;
import com.kara4k.mediagrub.model.inst.SearchRequestObj;
import com.kara4k.mediagrub.model.inst.photo.Node;
import com.kara4k.mediagrub.model.inst.photo.PhotoResponse;
import com.kara4k.mediagrub.model.inst.users.User;
import com.kara4k.mediagrub.model.inst.users.UsersResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InstagramApiTest {

    private static final String HTTPS = "https:";

    private static final String TEST_USER_NAME = "nature";
    private static final String TEST_SEARCH_WORD = "nature";
    private static final String TEST_USER_ID = "3998316794";
    private static final String TEST_VIDEO_SOURCE = "B68Xwy9lqBw";

    @Inject
    InstApi instApi;

    @Before
    public void setUp() {
        DaggerTestApiComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build().injectInstApiTest(this);
    }

    @Test
    public void getUserInfo() {
        final Observable<UsersResponse> response = instApi.getUserInfo(TEST_USER_NAME);
        final List<User> users = new ArrayList<>();

        response.singleOrError()
                .subscribe((userResponse) -> users.addAll(userResponse.getUsers()), this::onError);

        assertFalse(users.isEmpty());

        final String username = users.get(0).getUser().getUsername();
        Assert.assertEquals(username, TEST_USER_NAME);
    }

    @Test
    public void getDetailedIno() throws IOException {
        final String detailedInfoRequest = new RequestObject(TEST_VIDEO_SOURCE).create();
        final String videoUrl = instApi.getDetailedIno(detailedInfoRequest).execute()
                .body().getData().getShortcodeMedia().getVideoUrl();

        assertNotNull(videoUrl);
        assertTrue(videoUrl.startsWith(HTTPS));
    }

    @Test
    public void getPhotos() {
        final String request = new RequestObject(TEST_USER_ID, null).create();
        final Observable<PhotoResponse> response = instApi.getPhotos(request);
        final List<Node> nodes = new ArrayList<>();

        response.singleOrError()
                .subscribe(photoResponse -> {
            final Node node = photoResponse.getData().getUser()
                    .getEdgeOwnerToTimelineMedia().getEdges().get(0).getNode();

            nodes.add(node);
        }, this::onError);

        assertFalse(nodes.isEmpty());

        final Node node = nodes.get(0);

        assertTrue(node.getShortcode() != null && node.getDisplayUrl() != null);
        assertTrue(node.getDisplayUrl().startsWith(HTTPS));
    }

    @Test
    public void searchPhotos() {
        final String request = new SearchRequestObj(TEST_SEARCH_WORD, null).build();
        final List<com.kara4k.mediagrub.model.inst.search.Node> nodes = new ArrayList<>();

        instApi.searchPhotos(request).singleOrError()
                .subscribe((searchResponse) -> {
                    final com.kara4k.mediagrub.model.inst.search.Node node = searchResponse.getData()
                            .getHashtag().getEdgeHashtagToMedia().getEdges().get(0).getNode();

                    nodes.add(node);
                }, this::onError);

        assertFalse(nodes.isEmpty());

        final com.kara4k.mediagrub.model.inst.search.Node node = nodes.get(0);

        assertTrue(node.getDisplayUrl() != null
                && node.getThumbnailSrc() != null);
        assertTrue(node.getDisplayUrl().startsWith(HTTPS)
                && node.getThumbnailSrc().startsWith(HTTPS));
    }

    private Consumer<Throwable> onError(final Throwable throwable) {
        return throwable1 -> fail(throwable.getMessage());
    }
}