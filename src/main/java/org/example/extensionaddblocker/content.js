function skipAds() {
    const video = document.querySelector('video');

    // Detect if ad is playing
    const adShowing = document.querySelector('.ad-showing');

    if (adShowing && video) {
        // 🔥 Skip ad by jumping video
        video.currentTime = video.duration;

        // 🔇 Mute during ads
        video.muted = true;
    } else if (video) {
        // 🔊 Unmute when normal video
        video.muted = false;
    }

    // Click skip button if available
    const skipBtn = document.querySelector('.ytp-ad-skip-button');
    if (skipBtn) skipBtn.click();

    // Remove overlay ads
    document.querySelectorAll('.ytp-ad-overlay-container')
        .forEach(el => el.remove());
}

// Run very fast
setInterval(skipAds, 200);