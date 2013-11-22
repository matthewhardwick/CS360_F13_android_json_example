cs360_android_simple_json_parser
================================

To Add Android Async HTTP Client Library:

1. Download the JAR file from here: [http://loopj.com/android-async-http/](Android Asynchronous Http Client)
  * `android-async-http-1.4.4.jar`
1. Add a `lib` folder in your project at the same level as build, and src. `Right Click, New, Directory`
1. Drag and Drop the JAR file you downloaded into the `lib` folder
1. Right Click on the JAR File, and Select `Add as Library...`
1. There exist two build.gradle files, you want to edit the inner most one, in your `src` folder
1. in the `dependencies` section add the following line
  * `compile files('lib/android-async-http-1.4.4.jar')`
  * See: [https://github.com/matthewhardwick/cs360_android_simple_json_parser/blob/master/JSON_to_listview/build.gradle](https://github.com/matthewhardwick/cs360_android_simple_json_parser/blob/master/JSON_to_listview/build.gradle)
