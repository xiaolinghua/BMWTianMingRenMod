package modcore.Patches.render;

public class KeywordsColorPatch {
    /*
    @SpireEnum
    public static DialogWord.WordColor B1_COLOR;

    @SpirePatch(clz = DialogWord.class, method = "getColor")
    public static class PatchGetColor {
        public static SpireReturn<Color> Prefix(DialogWord dw) {
            Enum wColor = ReflectionHacks.getPrivate(dw, DialogWord.class, "wColor");
            if (wColor.name().equals("B1_COLOR")) {
                Color cpy = B1Mod.B1_COLOR.cpy();
                cpy.a = 1.0F;
                return SpireReturn.Return(cpy);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = DialogWord.class, method = "identifyWordColor")
    public static class PatchIdentifyWordColor {
        public static SpireReturn<DialogWord.WordColor> Prefix(String word) {
            if (word.charAt(0) == '#' && word.charAt(1) == 'n')
                return SpireReturn.Return(KeywordsColorPatch.B1_COLOR);
            return SpireReturn.Continue();
        }
    }

     */
}