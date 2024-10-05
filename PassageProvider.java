import java.util.Random;

public class PassageProvider {
    private static final String[] banglaPassages = {
        " কিন্তু গাছের এত উপরে থাকা আঙুরের থোকা গুলির সে কীভাবে নাগাল পাবে সেই নিয়ে ভাবতে শুরু করল । কিন্তু শিয়ালটি তার কোনও উপায়ই বার করতে পারল না। অবশেষে বিফল মনোরথ হয়ে সে সেই স্থান পরিত্যাগ করল।",
        "অবশেষে এক বৃদ্ধ ইঁদুর দাঁড়িয়ে বলল, “আমি একটা খুব ভালো উপায়ের কথা চিন্তা করেছি ।আমরা যদি বিড়ালটির গলায় ঘণ্টা বেঁধে দি তাহলে বিড়ালটা যে আসছে সেই ঘণ্টার  শব্দ শুনেই পরিষ্কারভাবে বোঝা যাবে।",
       "ফড়িংটি প্রতিদিনই পিঁপড়েকে  বিরতি নিতে বলত কিন্তু পিঁপড়ে নিজের কাজ চালিয়ে যেত। শীঘ্রই, শীত এল। রাতে ঠান্ডার প্রকোপ বেড়ে যাওয়ার ফলে খুব কম প্রাণী বাইরে বের হত। ফড়িংটি খাদ্যাভাবে সব সময় কষ্টে দিন যাপন করত ।"
        // Add more Bangla passages as needed
    };

    private static final String[] englishPassages = {
        "You're a little scary sometimes, you know that? Brilliant... but scary. Do not pity the dead, Harry. Pity the living, and above all, those who live without love.",
        "A Hare who ridicules a slow-moving Tortoise. Tired of the Hare's arrogant behaviour, the Tortoise challenges him to a race. The hare soon leaves the tortoise behind and, confident of winning, takes a nap midway through the race.",
        "Driven by hunger, a fox tried to reach some grapes hanging high on the vine but was unable to, although he leaped with all his strength. As he went away, the fox remarked 'Oh, you aren't even ripe yet! I don't need any sour grapes.",
          "A grasshopper that has spent the summer singing and dancing while the ant worked to store up food for winter. When winter arrives, the grasshopper finds itself dying of hunger and begs the ant for food. However, the ant rebukes its idleness and tells it to dance the winter away now."
        // Add more English passages as needed
    };

    public static String getBanglaPassage() {
        return getRandomPassage(banglaPassages);
    }

    public static String getEnglishPassage() {
        return getRandomPassage(englishPassages);
    }

    private static String getRandomPassage(String[] passages) {
        Random random = new Random();
        return passages[random.nextInt(passages.length)];
    }
}
