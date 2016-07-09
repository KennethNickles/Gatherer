package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

/**
 * @author kenneth.nickles
 * @since 2016-06-26.
 */
@Parceled
public class Set implements Parcelable {

    public static final Parcelable.Creator<Set> CREATOR = Postman.getCreator(Set.class);

    Builder mState;

    Set() {
        // Postman
    }

    private Set(Builder state) {
        this.mState = state;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return mState.mId;
    }

    public String getName() {
        return mState.mName;
    }

    public String getBorder() {
        return mState.mBorder;
    }

    public String getType() {
        return mState.mType;
    }

    public String getUrl() {
        return mState.mUrl;
    }

    public String getCardsUrl() {
        return mState.mCardsUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Postman.writeToParcel(this, dest);
    }

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        String mId;
        String mName;
        String mBorder;
        String mType;
        String mUrl;
        String mCardsUrl;

        Builder() {
            // Postman
        }

        @NonNull
        public Builder withId(@NonNull String id) {
            this.mId = Preconditions.checkNotNull(id, "Id");
            return this;
        }

        @NonNull
        public Builder withName(@NonNull String name) {
            this.mName = Preconditions.checkNotNull(name, "Name");
            return this;
        }

        @NonNull
        public Builder withBorder(@NonNull String border) {
            this.mBorder = Preconditions.checkNotNull(border, "Border");
            return this;
        }

        @NonNull
        public Builder withType(@NonNull String type) {
            this.mType = Preconditions.checkNotNull(type, "Type");
            return this;
        }

        @NonNull
        public Builder withUrl(@NonNull String url) {
            this.mUrl = Preconditions.checkNotNull(url, "Url");
            return this;
        }

        @NonNull
        public Builder withCardsUrl(@NonNull String cardsUrl) {
            this.mCardsUrl = Preconditions.checkNotNull(cardsUrl, "CardsUrl");
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            Postman.writeToParcel(this, dest);
        }

        @NonNull
        public Set build() {
            return new Set(this);
        }
    }
}
