package site.iway.javahelpers;

public enum YAlign {

    TopTop,
    TopCenter,
    TopBottom,
    CenterTop,
    CenterCenter,
    CenterBottom,
    BottomTop,
    BottomCenter,
    BottomBottom;

    public int getY(int baseY, int baseHeight, int height) {
        switch (this) {
            case TopTop:
                return baseY;
            case TopCenter:
                return baseY - height / 2;
            case TopBottom:
                return baseY - height;
            case CenterTop:
                return baseY + baseHeight / 2;
            case CenterCenter:
                return baseY + (baseHeight - height) / 2;
            case CenterBottom:
                return baseY + baseHeight / 2 - height;
            case BottomTop:
                return baseY + baseHeight;
            case BottomCenter:
                return baseY + baseHeight - height / 2;
            case BottomBottom:
                return baseY + baseHeight - height;
            default:
                return baseY + (baseHeight - height) / 2;
        }
    }

    public float getY(float baseY, float baseHeight, float height) {
        switch (this) {
            case TopTop:
                return baseY;
            case TopCenter:
                return baseY - height / 2;
            case TopBottom:
                return baseY - height;
            case CenterTop:
                return baseY + baseHeight / 2;
            case CenterCenter:
                return baseY + (baseHeight - height) / 2;
            case CenterBottom:
                return baseY + baseHeight / 2 - height;
            case BottomTop:
                return baseY + baseHeight;
            case BottomCenter:
                return baseY + baseHeight - height / 2;
            case BottomBottom:
                return baseY + baseHeight - height;
            default:
                return baseY + (baseHeight - height) / 2;
        }
    }

}