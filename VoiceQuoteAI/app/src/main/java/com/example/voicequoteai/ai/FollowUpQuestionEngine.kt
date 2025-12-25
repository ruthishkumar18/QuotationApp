package com.example.voicequoteai.ai

class FollowUpQuestionEngine {

    fun getQuestions(result: AIResult): List<String> {

        val questions = mutableListOf<String>()

        if (result.advancePercent == null) {
            questions.add("What is the advance payment percentage?")
        }

        if (result.deliveryTimeline == null) {
            questions.add("What is the delivery timeline?")
        }

        if (result.amount <= 0) {
            questions.add("What is the total amount?")
        }

        return questions
    }
}
