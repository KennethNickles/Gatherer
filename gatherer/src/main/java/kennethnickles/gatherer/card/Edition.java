package kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import kennethnickles.gatherer.util.GsonUtils;
import kennethnickles.gatherer.util.Lists;
import kennethnickles.gatherer.util.Preconditions;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import java.util.ArrayList;

/**
 * @author kenneth.nickles
 * @since 2016-04-03.
 */
@Parceled
public class Edition implements Parcelable {

    public static final Creator<Edition> CREATOR = Postman.getCreator(Edition.class);

    Builder mState;

    private Edition(Builder builder) {
        this.mState = builder;
    }

    Edition() {
        // Postman
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

    public int getNumber() {
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

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        String mSet;
        String mSetId;
        Rarity mRarity;
        String mArtist;
        int mMulitverseId;
        String mFlavorText;
        int mNumber;
        String mLayout;
        String mImageUrl;
        String mSetUrl;
        String mStoreUrl;

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
        public Builder withNumber(int number) {
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

        @NonNull
        Builder recycle() {
            mSet = null;
            mSetId = null;
            mRarity = null;
            mArtist = null;
            mMulitverseId = 0;
            mFlavorText = null;
            mNumber = 0;
            mLayout = null;
            mImageUrl = null;
            mSetUrl = null;
            mStoreUrl = null;
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
    }

    public static class Deserializer implements JsonDeserializer<ArrayList<Edition>> {

        @Override
        public ArrayList<Edition> deserialize(JsonElement json,
                                              java.lang.reflect.Type typeOfT,
                                              JsonDeserializationContext context)
                throws JsonParseException {

            final ArrayList<Edition> editions = Lists.newArrayList();
            final JsonArray jsonArray = json.getAsJsonObject().get("editions").getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                final Builder builder = new Builder();
                final JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (GsonUtils.isNonNull(jsonObject.get("set"))) {
                    builder.withSet(jsonObject.get("set").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("set_id"))) {
                    builder.withSetId(jsonObject.get("set_id").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("rarity"))) {
                    builder.withRarity(jsonObject.get("rarity").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("artist"))) {
                    builder.withArtist(jsonObject.get("artist").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("multiverse_id"))) {
                    builder.withMultiverseId(jsonObject.get("multiverse_id").getAsInt());
                }
                if (GsonUtils.isNonNull(jsonObject.get("flavor"))) {
                    builder.withFlavorText(jsonObject.get("flavor").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("number"))) {
                    builder.withNumber(jsonObject.get("number").getAsInt());
                }
                if (GsonUtils.isNonNull(jsonObject.get("layout"))) {
                    builder.withLayout(jsonObject.get("layout").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("image_url"))) {
                    builder.withImageUrl(jsonObject.get("image_url").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("set_url"))) {
                    builder.withSetUrl(jsonObject.get("set_url").getAsString());
                }
                if (GsonUtils.isNonNull(jsonObject.get("store_url"))) {
                    builder.withStoreUrl(jsonObject.get("store_url").getAsString());
                }
                editions.add(builder.build());
            }
            return editions;
        }
    }
}
