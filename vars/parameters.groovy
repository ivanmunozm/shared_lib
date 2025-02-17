def call() {
    return [
        choice(name: 'action', choices: 'create\ndelete', description: 'Choice Action: create/delete'),
        string(name: 'ImageName', description: "Name of the Docker build", defaultValue: 'javappspring'),
        string(name: 'ImageTag', description: "Tag of the Docker build", defaultValue: 'v1'),
        string(name: 'DockerHubUser', description: "Docker Hub Username", defaultValue: 'gusanorock')
    ]
}
