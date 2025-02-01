def call(Map stageParams) {
    checkout([
        $class: 'GitSCM',
        branches: [[name: stageParams.branch]],
        userRemoteConfigs: [[url: stageParams.url]]
    ])
    echo "Checked out branch '${stageParams.branch}' from '${stageParams.url}'"
}
