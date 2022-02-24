void call(){

  def podTemplate = """
  apiVersion: v1
  kind: Pod
  spec:
    containers:
    - name: alpine
      image: alpine
      command:
      - sleep
      args:
      - infinity
      volumeMounts:
        - name: workspace-volume
          mountPath: /home/jenkins/agent
      workingDir: "/home/jenkins/agent"
    volumes:
        - name: "workspace-volume"
          persistentVolumeClaim:
            claimName: "jenkins-slave-pvc"
            readOnly: false
  """

  agent {
    kubernetes {
      yaml podTemplate
      workspaceVolume dynamicPVC(accessModes: 'ReadWriteOnce', requestsSize: "10Gi")
    }
  }
  stage('checkout') {
    deleteDir()
    def checkout = checkout(scm)
  }

}