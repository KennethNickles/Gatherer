package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

/**
 * @author kenneth.nickles
 * @since 2016-04-03.
 */
@Parceled
public class Edition implements Parcelable {

    public static final Creator<Edition> CREATOR = Postman.getCreator(Edition.class);

    Builder mState;

    Edition() {
        // Postman
    }

    private Edition(Builder builder) {
        this.mState = builder;
    }

    public String getSet() {
        return mState.mSet;
    }

    public String getSetId() {
        return mState.mSetId;
    }

    public Rarity getRarity() {
        return mState.mRarity;
    }

    public String getArtist() {
        return mState.mArtist;
    }

    public int getMultiverseId() {
        return mState.mMulitverseId;
    }

    public String getFlavorText() {
        return mState.mFlavorText;
    }

    public String getNumber() {
        return mState.mNumber;
    }

    public String getLayout() {
        return mState.mLayout;
    }

    public String getImageUrl() {
        return mState.mImageUrl;
    }

    public String getSetUrl() {
        return mState.mSetUrl;
    }

    public String getStoreUrl() {
        return mState.mStoreUrl;
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
    public static Builder builder() {
        return new Builder();
    }

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        String mSet;
        String mSetId;
        Rarity mRarity;
        String mArtist;
        int mMulitverseId;
        String mFlavorText;
        String mNumber;
        String mLayout;
        String mImageUrl;
        String mSetUrl;
        String mStoreUrl;

        Builder() {
            // Postman
        }

        @NonNull
        public Builder withSet(@NonNull String set) {
            this.mSet = Preconditions.checkNotNull(set, "Set");
            return this;
        }

        @NonNull
        public Builder withSetId(@NonNull String setId) {
            this.mSetId = Preconditions.checkNotNull(setId, "SetId");
            return this;
        }

        @NonNull
        public Builder withRarity(@NonNull String rarity) {
            this.mRarity = Preconditions.checkNotNull(Rarity.from(rarity), "Rarity");
            return this;
        }

        @NonNull
        public Builder withArtist(@NonNull String artist) {
            this.mArtist = Preconditions.checkNotNull(artist, "Artist");
            return this;
        }

        @NonNull
        public Builder withMultiverseId(int multiverseId) {
            this.mMulitverseId = Preconditions.checkNotNull(multiverseId, "MultiverseId");
            return this;
        }

        @NonNull
        public Builder withFlavorText(@NonNull String flavorText) {
            this.mFlavorText = flavorText;
            return this;
        }

        @NonNull
        public Builder withNumber(@NonNull String number) {
            this.mNumber = number;
            return this;
        }

        @NonNull
        public Builder withLayout(@NonNull String layout) {
            this.mLayout = layout;
            return this;
        }

        @NonNull
        public Builder withImageUrl(@NonNull String imageUrl) {
            this.mImageUrl = imageUrl;
            return this;
        }

        @NonNull
        public Builder withSetUrl(@NonNull String setUrl) {
            this.mSetUrl = setUrl;
            return this;
        }

        @NonNull
        public Builder withStoreUrl(@NonNull String storeUrl) {
            this.mStoreUrl = storeUrl;
            return this;
        }

        @NonNull
        public Edition build() {
            return new Edition(this);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            Postman.writeToParcel(this, dest);
        }
    }
}
