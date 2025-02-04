def call(String credentialsId) {
    script {
        def qualityGate = waitForQualityGate()
        if (qualityGate.status != 'OK') {
            error "❌ SonarQube Quality Gate failed: ${qualityGate.status}"
        } else {
            echo "✅ SonarQube Quality Gate passed!"
        }
    }
}
