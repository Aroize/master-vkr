# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn kotlin.**
-dontwarn com.facebook.proguard.annotations.DoNotStrip
-dontwarn com.huawei.hms.ads.identifier.AdvertisingIdClient$Info
-dontwarn com.huawei.hms.ads.identifier.AdvertisingIdClient
-dontwarn com.my.tracker.MyTracker
-dontwarn com.my.tracker.MyTrackerParams
-dontwarn com.my.tracker.miniapps.MiniAppEvent
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder$CloseEventBuilder
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder$CustomEventBuilder
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder$EventBuilder
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder$OpenEventBuilder
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder$UserEventBuilder
-dontwarn com.my.tracker.miniapps.MiniAppEventBuilder
-dontwarn com.vk.auth.verification.libverify.DefaultLogReceiver
-dontwarn com.vk.auth.verification.libverify.LibverifyControllerProviderImpl
-dontwarn com.vk.location.LocationUtils
-dontwarn com.vk.oauth.mail.MailSilentAuthInfoProvider
-dontwarn com.vk.stat.utils.VkEventFilter
-dontwarn com.vk.stat.utils.VkEventGenerator
-dontwarn com.vk.superapp.advertisement.AdvertisementControllerImpl
-dontwarn com.vk.superapp.advertisement.AdvertismentBannerHelper
-dontwarn com.vk.superapp.advertisement.bridges.DefaultSuperappAdBridge
-dontwarn com.vk.superapp.apps.SuperappCatalogActivity$Companion
-dontwarn com.vk.superapp.apps.SuperappCatalogActivity
-dontwarn com.vk.superapp.exception.SuperappkitUncaughtExceptionHandler
-dontwarn com.vk.superapp.multiaccount.impl.MultiAccountComponentImpl$Factory
-dontwarn com.vk.superapp.navigation.VkBridgeAnalytics$BannerAdEvent
-dontwarn com.vk.superapp.navigation.VkBridgeAnalytics$PersonalDiscountEvent
-dontwarn com.vk.superapp.navigation.VkBridgeAnalytics$RegistrationEvent
-dontwarn com.vk.superapp.navigation.VkBridgeAnalytics
-dontwarn com.vk.superapp.navigation.VkBrowserAnalytics
-dontwarn com.vk.superapp.navigation.VkBrowserNavigationAnalytics
-dontwarn com.vk.superapp.navigation.data.AppShareType
-dontwarn com.vk.superapp.perf.time.SuperAppKitPerformanceChecker
-dontwarn com.vk.superapp.perf.time.SuperAppKitPerformanceRouter$Companion
-dontwarn com.vk.superapp.perf.time.SuperAppKitPerformanceRouter
-dontwarn com.vk.superapp.vkpay.VkPayTokenStorage
-dontwarn com.vk.superapp.vkpay.VkTouchIdHelper$Builder
-dontwarn com.vk.superapp.vkpay.VkTouchIdHelper$Companion
-dontwarn com.vk.superapp.vkpay.VkTouchIdHelper
-dontwarn com.vk.superapp.vkpay.checkout.CardsAndUsersFixture
-dontwarn com.vk.superapp.vkpay.checkout.DummyUser
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutErrorReason$TransactionFailed
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutErrorReason$UserCancelled
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutErrorReason
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutFailed$VkCheckoutError
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutFailed
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutResult
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutResultDisposable
-dontwarn com.vk.superapp.vkpay.checkout.VkCheckoutSuccess
-dontwarn com.vk.superapp.vkpay.checkout.VkPayCheckout$Companion
-dontwarn com.vk.superapp.vkpay.checkout.VkPayCheckout
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkExtraPaymentOptions
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkMerchantInfo
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkOrderDescription$Description
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkOrderDescription$NoDescription
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkOrderDescription
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkTransactionInfo$Currency
-dontwarn com.vk.superapp.vkpay.checkout.api.dto.model.VkTransactionInfo
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig$Domain
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig$Environment$Production
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig$Environment$ProductionWithTestMerchant
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig$Environment$Sandbox
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig$Environment
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfig
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutConfigBuilder
-dontwarn com.vk.superapp.vkpay.checkout.config.VkPayCheckoutParams
-dontwarn com.vk.superapp.vkpay.checkout.data.VkCheckoutUserInfo
-dontwarn kotlinx.parcelize.Parcelize
-dontwarn ru.mail.libverify.api.VerificationFactory
-dontwarn ru.mail.verify.core.utils.LogReceiver

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
-repackageclasses
-ignorewarnings
## Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
## EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
## Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
## Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault
## Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
## Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
## Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
## Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
## With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
## and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
## Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>
## Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
## With R8 full mode generic signatures are stripped for classes that are not
## kept. Suspend functions are wrapped in continuations where the type argument
## is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
## Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
##-keep public class com.itextpdf.**
-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.*
-renamesourcefileattribute SourceFile
-dontwarn retrofit2.**
-keep class retrofit2.** {*;}

-keep class care.visify.api.models.** { <fields>; }
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }
# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type
# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
##---------------End: proguard configuration for Gson  ----------
#-dontwarn org.slf4j.**
#-keepdirectories src/main/res/font/*
#-dontusemixedcaseclassnames
#-verbose
## ADDED
#-dontshrink
#-dontobfuscate
#-keepattributes *Annotation*
## For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
## keep setters in Views so that animations can still work.
## see http://proguard.sourceforge.net/manual/examples.html#beans
#-keepclassmembers public class * extends android.view.View {
#   void set*(***);
#   *** get*();
#}
## We want to keep methods in Activity that could be used in the XML attribute onClick
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
## For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
## Firebase
-keep class com.google.android.gms.** { *; }
-keep class com.google.firebase.** { *; }
## in order to provide the most meaningful crash reports, add the following line:
-keepattributes SourceFile,LineNumberTable
## If you are using custom exceptions, add this line so that custom exception types are skipped during obfuscation:
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
## General
-keepattributes SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,Signature,Exceptions,InnerClasses