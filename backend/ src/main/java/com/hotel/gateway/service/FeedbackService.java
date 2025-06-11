public List<Feedback> getAllFeedback() {
    return feedbackRepository.findAll();
}

public void deleteFeedback(Long id) {
    feedbackRepository.deleteById(id);
}

public void approveFeedback(Long id) {
    Feedback feedback = feedbackRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Feedback not found"));
    feedback.setApproved(true);
    feedbackRepository.save(feedback);
}
