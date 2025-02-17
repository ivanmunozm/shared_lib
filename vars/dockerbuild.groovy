def call(String project, String ImageTag, String hubUser) {
    if (!hubUser?.trim()) {
        error "❌ ERROR: El usuario de Docker Hub (hubUser) no está definido."
    }

    sh """
       echo "🚀 Construyendo imagen Docker..."
       docker image build -t ${hubUser}/${project} .
       
       echo "🏷️ Etiquetando imagen..."
       docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
       docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
       
       echo "📤 Subiendo imagen a Docker Hub..."
       docker login -u ${hubUser} -p ${env.DOCKERHUB_PASSWORD}
       docker image push ${hubUser}/${project}:${ImageTag}
    """
}
