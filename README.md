# MathView 
`MathView` is a library to render Math equations in Android. It uses [jqMath](https://mathscribe.com/author/jqmath.html) to render math equations.

## Setup

Add `compile ____` into **dependencies** section of your **module** build.gradle file. For example:

```groovy
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile 'com.android.support:appcompat-v7:23.0.0'
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

## How to

To learn how to write math equations in it, please have a look at [jqMath](https://mathscribe.com/author/jqmath.html).

## Advantages

1. Faster than MathJax.
2. Change text size and color easily.
3. Supports HTML outside of the equation. Example 
  ```java
  mathview.setText("This is a <b>straight</b> line, $\ax + \by = \c$");
  ```
4. Has `OnClickListener`

## Disadvantages

1. Special symbols are typed manually. Example: √ ∑ ∫ ← → + >
2. Some parts of the MathML standard are not yet implemented in jqMath, such as elementary school mathematics (e.g. “long division”), and “Content MathML.”
