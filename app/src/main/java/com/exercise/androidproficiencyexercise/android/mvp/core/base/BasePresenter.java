package com.exercise.androidproficiencyexercise.android.mvp.core.base;

/**
 * Created by Akash on 20-05-2018.
 */

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * All presenter should extend this. Contains common func of presenter.
 * Stores view as weak reference.
 * @param <V>
 */

public class BasePresenter <V extends BaseScreen> {
    private WeakReference<V> viewRef;

    @UiThread
    public void attachScreen(V view) {
        viewRef = new WeakReference<>(view);
    }

    /**
     * Get the attached screen. You should always call {@link #isScreenAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */

    @UiThread
    @Nullable
    public V getScreen() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getScreen()} to get the view instance.
     */
    @UiThread
    public boolean isScreenAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @UiThread
    public void detachScreen() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
