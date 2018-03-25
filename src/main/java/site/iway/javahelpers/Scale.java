package site.iway.javahelpers;

public enum Scale {

    None,
    Center,
    CenterFit,
    CenterCrop,
    CenterInside,
    FitWidth,
    FitHeight;

    public float getScale(float width, float height, float bitmapWidth, float bitmapHeight) {
        float targetScale = 1.0f;
        switch (this) {
            case None:
            case Center:
                break;
            case CenterInside:
                if (bitmapWidth <= width && bitmapHeight <= height)
                    break;
            case CenterFit:
                if (width / bitmapWidth * bitmapHeight <= height)
                    targetScale = width / bitmapWidth;
                if (height / bitmapHeight * bitmapWidth <= width)
                    targetScale = height / bitmapHeight;
                break;
            case CenterCrop:
                if (width / bitmapWidth * bitmapHeight >= height)
                    targetScale = width / bitmapWidth;
                if (height / bitmapHeight * bitmapWidth >= width)
                    targetScale = height / bitmapHeight;
                break;
            case FitWidth:
                targetScale = width / bitmapWidth;
                break;
            case FitHeight:
                targetScale = height / bitmapHeight;
                break;
        }
        return targetScale;
    }

    public float getScaledWidth(float width, float height, float bitmapWidth, float bitmapHeight) {
        return getScale(width, height, bitmapWidth, bitmapHeight) * bitmapWidth;
    }

    public float getScaledHeight(float width, float height, float bitmapWidth, float bitmapHeight) {
        return getScale(width, height, bitmapWidth, bitmapHeight) * bitmapHeight;
    }

}
