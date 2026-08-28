class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Count characters of s
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Find middle character
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (count[i] % 2 == 1) {

                // More than one odd character
                // means palindrome is impossible
                if (middle != 0) {
                    return "";
                }

                middle = (char) ('a' + i);

                // Remove middle character
                count[i]--;
            }
        }

        int half = n / 2;

        /*
         * Try to make the left half equal to
         * target's left half.
         *
         * Each character is needed twice because
         * palindrome has the same character on both sides.
         */
        for (int i = 0; i < half; i++) {

            int c = target.charAt(i) - 'a';

            count[c] -= 2;
        }

        // Number of characters whose count became negative
        int negative = 0;

        // Largest character still available
        int largest = -1;

        for (int i = 0; i < 26; i++) {

            if (count[i] < 0) {
                negative++;
            } 
            else if (count[i] > 0) {
                largest = Math.max(largest, i);
            }
        }

        /*
         * Case 1:
         * Target's left half can be used completely.
         *
         * Check whether the palindrome made from
         * target's left half is already > target.
         */
        if (negative == 0) {

            String left = target.substring(0, half);

            String right = new StringBuilder(left)
                    .reverse()
                    .toString();

            String targetRight = target.substring(half);

            String candidateRight = "";

            if (middle != 0) {
                candidateRight += middle;
            }

            candidateRight += right;

            /*
             * Left half is exactly equal to target's
             * left half, so only right side matters.
             */
            if (candidateRight.compareTo(targetRight) > 0) {

                return left + candidateRight;
            }
        }

        /*
         * Case 2:
         *
         * We need to change something in the left half.
         *
         * Start from the RIGHTMOST position and move left.
         *
         * This gives the smallest possible answer.
         */
        for (int i = half - 1; i >= 0; i--) {

            int c = target.charAt(i) - 'a';

            // Give back the character we previously used
            count[c] += 2;

            // Update negative count
            if (count[c] == 0) {
                negative--;
            }

            // Update largest available character
            if (count[c] == 2) {
                largest = Math.max(largest, c);
            }

            /*
             * If some required character is still missing,
             * we cannot use this position.
             *
             * Or if there is no character greater than
             * target[i], we cannot make the answer bigger.
             */
            if (negative > 0 || largest <= c) {
                continue;
            }

            /*
             * Find the SMALLEST character greater than
             * target[i].
             */
            int bigger = c + 1;

            while (count[bigger] == 0) {
                bigger++;
            }

            // Use that bigger character
            count[bigger] -= 2;

            /*
             * Build left half:
             *
             * target[0 ... i-1]
             * + bigger character
             * + smallest remaining characters
             */
            StringBuilder left = new StringBuilder(
                    target.substring(0, i)
            );

            left.append((char) ('a' + bigger));

            // Fill remaining positions with smallest chars
            for (int ch = 0; ch < 26; ch++) {

                while (count[ch] > 0) {

                    left.append((char) ('a' + ch));

                    count[ch] -= 2;
                }
            }

            /*
             * Build complete palindrome
             */
            String leftPart = left.toString();

            String rightPart = new StringBuilder(leftPart)
                    .reverse()
                    .toString();

            StringBuilder answer = new StringBuilder();

            answer.append(leftPart);

            // Middle character for odd length
            if (middle != 0) {
                answer.append(middle);
            }

            answer.append(rightPart);

            return answer.toString();
        }

        // No valid palindrome exists
        return "";
    }
}