package site.iway.javahelpers;

public enum XAlign {

    LeftLeft,
    LeftCenter,
    LeftRight,
    CenterLeft,
    CenterCenter,
    CenterRight,
    RightLeft,
    RightCenter,
    RightRight;

    public int getX(int baseX, int baseWidth, int width) {
        switch (this) {
            case LeftLeft:
                return baseX;
            case LeftCenter:
                return baseX - width / 2;
            case LeftRight:
                return baseX - width;
            case CenterLeft:
                return baseX + baseWidth / 2;
            case CenterCenter:
                return baseX + (baseWidth - width) / 2;
            case CenterRight:
                return baseX + baseWidth / 2 - width;
            case RightLeft:
                return baseX + baseWidth;
            case RightCenter:
                return baseX + baseWidth - width / 2;
            case RightRight:
                return baseX + baseWidth - width;
            default:
                return baseX + (baseWidth - width) / 2;
        }
    }

    public float getX(float baseX, float baseWidth, float width) {
        switch (this) {
            case LeftLeft:
                return baseX;
            case LeftCenter:
                return baseX - width / 2;
            case LeftRight:
                return baseX - width;
            case CenterLeft:
                return baseX + baseWidth / 2;
            case CenterCenter:
                return baseX + (baseWidth - width) / 2;
            case CenterRight:
                return baseX + baseWidth / 2 - width;
            case RightLeft:
                return baseX + baseWidth;
            case RightCenter:
                return baseX + baseWidth - width / 2;
            case RightRight:
                return baseX + baseWidth - width;
            default:
                return baseX + (baseWidth - width) / 2;
        }
    }

}