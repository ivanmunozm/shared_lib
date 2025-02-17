def call() {
    // Obtener parámetros de Jenkins
    def project = env.ImageName ?: "javappspring"
    def ImageTag = env.ImageTag ?: "v1"
    def hubUser = env.DockerHubUser ?: "gusanorock"

    if (!hubUser?.trim()) {
        error "❌ ERROR: El usuario de Docker Hub (hubUser) no está definido."
    }

    withCredentials([string(credentialsId: 'DOCKERHUB_PASSWORD', variable: 'DOCKERHUB_PASSWORD')]) {
        sh """
           echo "🚀 Construyendo imagen Docker para ${hubUser}/${project}:${ImageTag}..."
           docker image build -t ${hubUser}/${project} .
           
           echo "🏷️ Etiquetando imagen..."
           docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
           docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
           
           echo "📤 Autenticando en Docker Hub..."
           docker login -u ${hubUser} -p ${DOCKERHUB_PASSWORD}

           echo "📤 Subiendo imagen a Docker Hub..."
           docker image push ${hubUser}/${project}:${ImageTag}
        """
    }
}
