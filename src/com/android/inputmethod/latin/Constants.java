/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.inputmethod.latin;

public final class Constants {
    public static final class Color {
        /**
         * The alpha value for fully opaque.
         */
        public final static int ALPHA_OPAQUE = 255;
    }

    public static final class Subtype {
        /**
         * The subtype mode used to indicate that the subtype is a keyboard.
         */
        public static final String KEYBOARD_MODE = "keyboard";

        public static final class ExtraValue {
            /**
             * The subtype extra value used to indicate that the subtype keyboard layout is capable
             * for typing ASCII characters.
             */
            public static final String ASCII_CAPABLE = "AsciiCapable";

            /**
             * The subtype extra value used to indicate that the subtype keyboard layout is capable
             * for typing EMOJI characters.
             */
            public static final String EMOJI_CAPABLE = "EmojiCapable";
            /**
             * The subtype extra value used to indicate that the subtype require network connection
             * to work.
             */
            public static final String REQ_NETWORK_CONNECTIVITY = "requireNetworkConnectivity";

            /**
             * The subtype extra value used to indicate that the subtype display name contains "%s"
             * for replacement mark and it should be replaced by this extra value.
             * This extra value is supported on JellyBean and later.
             */
            public static final String UNTRANSLATABLE_STRING_IN_SUBTYPE_NAME =
                    "UntranslatableReplacementStringInSubtypeName";

            /**
             * The subtype extra value used to indicate that the subtype keyboard layout set name.
             * This extra value is private to LatinIME.
             */
            public static final String KEYBOARD_LAYOUT_SET = "KeyboardLayoutSet";

            /**
             * The subtype extra value used to indicate that the subtype is additional subtype
             * that the user defined. This extra value is private to LatinIME.
             */
            public static final String IS_ADDITIONAL_SUBTYPE = "isAdditionalSubtype";

            private ExtraValue() {
                // This utility class is not publicly instantiable.
            }
        }

        private Subtype() {
            // This utility class is not publicly instantiable.
        }
    }

    public static final int NOT_A_CODE = -1;

    public static final int NOT_A_COORDINATE = -1;
    public static final int SUGGESTION_STRIP_COORDINATE = -2;
    public static final int SPELL_CHECKER_COORDINATE = -3;
    public static final int EXTERNAL_KEYBOARD_COORDINATE = -4;

    // A hint on how many characters to cache from the TextView. A good value of this is given by
    // how many characters we need to be able to almost always find the caps mode.
    public static final int EDITOR_CONTENTS_CACHE_SIZE = 1024;

    // Must be equal to MAX_WORD_LENGTH in native/jni/src/defines.h
    public static final int DICTIONARY_MAX_WORD_LENGTH = 48;

    public static boolean isValidCoordinate(final int coordinate) {
        // Detect {@link NOT_A_COORDINATE}, {@link SUGGESTION_STRIP_COORDINATE},
        // and {@link SPELL_CHECKER_COORDINATE}.
        return coordinate >= 0;
    }

    /**
     * Custom request code used in
     * {@link com.android.inputmethod.keyboard.KeyboardActionListener#onCustomRequest(int)}.
     */
    // The code to show input method picker.
    public static final int CUSTOM_CODE_SHOW_INPUT_METHOD_PICKER = 1;

    /**
     * Some common keys code. Must be positive.
     */
    public static final int CODE_ENTER = '\n';
    public static final int CODE_TAB = '\t';
    public static final int CODE_SPACE = ' ';
    public static final int CODE_PERIOD = '.';
    public static final int CODE_ARMENIAN_PERIOD = 0x0589;
    public static final int CODE_DASH = '-';
    public static final int CODE_SINGLE_QUOTE = '\'';
    public static final int CODE_DOUBLE_QUOTE = '"';
    public static final int CODE_QUESTION_MARK = '?';
    public static final int CODE_EXCLAMATION_MARK = '!';
    public static final int CODE_SLASH = '/';
    public static final int CODE_COMMERCIAL_AT = '@';
    public static final int CODE_PLUS = '+';
    public static final int CODE_CLOSING_PARENTHESIS = ')';
    public static final int CODE_CLOSING_SQUARE_BRACKET = ']';
    public static final int CODE_CLOSING_CURLY_BRACKET = '}';
    public static final int CODE_CLOSING_ANGLE_BRACKET = '>';

    private Constants() {
        // This utility class is not publicly instantiable.
    }

}
