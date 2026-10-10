
package live_coding_session_5.assignment_problems.problem1;

public class AccessChecker {

    public static String classifyAccess(String fieldModifier,
                                        String accessorContext) {
        switch (fieldModifier.toLowerCase()) {
            case "private":
                return accessorContext.equals("SAME_CLASS")
                        ? "ALLOWED" : "DENIED";

            case "default":
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equalsIgnoreCase(modifiers[i])) {
                    if (classifyAccess(attempt[0], attempt[1])
                            .equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (i > 0) {
                result.append(" | ");
            }

            result.append(modifiers[i]).append(": ")
                  .append(allowed).append(" allowed / ")
                  .append(denied).append(" denied");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(summarizeByModifier(attempts));
    }
}
