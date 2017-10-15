-printmapping proguard.map
-overloadaggressively
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes InnerClasses,EnclosingMethod
-dontnote
-dontwarn
-repackageclasses ''
-allowaccessmodification
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}