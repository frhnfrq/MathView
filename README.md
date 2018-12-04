[![](https://jitpack.io/v/frhnfrq/MathView.svg)](https://jitpack.io/#frhnfrq/MathView)

# MathView 

`MathView` is a library to render Math equations in Android. It uses [jqMath](https://mathscribe.com/author/jqmath.html) to render math equations.

## Setup

Add it in your **root** build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add `implementation 'com.github.frhnfrq:MathView:1.1'` into **dependencies** section of your **module** build.gradle file. For example:

```groovy
dependencies {
    implementation 'com.github.frhnfrq:MathView:1.1'
}
```
## Usage

#### Add `MathView` in your layout

```xml
<com.zanvent.mathview.MathView
    android:id="@+id/mathview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

#### Get an instance of it in your code
```java
MathView mathview = findViewById(R.id.mathview);
mathview.setText("If $ax^2+bx+c=0$ with $a≠0$, then: $$x={-b±√{b^2-4ac}}/{2a}$$");
mathview.setPixelScaleType(Scale.SCALE_DP);
mathview.setTextSize(16);
mathview.setTextColor("#111111");
```

## Screenshot
<img src="screenshots/screenshot.png" width="270px" height="480px" />

## How to

To learn how to write math equations in it, please have a look at [jqMath](https://mathscribe.com/author/jqmath.html).

## Advantages

1. Faster than MathJax.
2. Change text size and color easily.
3. Supports HTML outside of the equation. Example 
  ```java
  mathview.setText("This is a <b>straight</b> line, $\ax + \by = \c$");
  ``` 
<img src="screenshots/screenshot2.png" width="270px" height="480px" />

## Disadvantages

1. Special symbols are typed manually. Example: √ ∑ ∫ ← → + >
2. Some parts of the MathML standard are not yet implemented in jqMath, such as elementary school mathematics (e.g. “long division”), and “Content MathML.”




License
=======

     Copyright 2018 Farhan Farooqui
     
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
