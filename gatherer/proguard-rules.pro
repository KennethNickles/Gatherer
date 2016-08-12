# Add project specific ProGuard oracles here.
# By default, the flags in this file are appended to flags specified
# in /Users/kenneth.nickles/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Parcelable Generated Code
-keepclassmembers class * implements android.os.Parcelable { static ** CREATOR; }
-keepnames class * implements android.os.Parcelable { public static final ** CREATOR; }

# Postman Generated Code
-keep class * implements com.workday.postman.parceler.Parceler

-keepclassmembers class * implements android.os.Parcelable { static ** CREATOR; }

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Retrofit
-dontwarn retrofit2.**
-dontwarn okio.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
