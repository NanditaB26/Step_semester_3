
package live_coding_session_5.class_problems;

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier.toLowerCase()) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] requests) {
        int allowed = 0;
        int denied = 0;

        for (String[] request : requests) {
            if (classifyAccess(request[0], request[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("private", "SAME_PACKAGE"));

        String[][] requests = {
            {"public", "SAME_PACKAGE"},
            {"private", "SAME_CLASS"},
            {"default", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(requests));
    }
}
