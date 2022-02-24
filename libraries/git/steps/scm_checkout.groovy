void call(){

  podTemplate(
    containers: [
      containerTemplate(
        name: 'alpine', 
        image: 'alpine:latest', 
        command: 'sleep', 
        args: '30d'
      )
    ],
    volumes: [
      dynamicPVC(
        accessModes: 'ReadWriteOnce', 
        mountPath: '/home/jenkins/agent', 
        requestsSize: '10Gi', 
        storageClassName: 'hostpath'
      )
    ]
  ) 
  
  {
    node(POD_LABEL) {
      container('alpine') {
        stage('checkout') {
          deleteDir()
          def checkout = checkout(scm)
        }
      }
    }  
  }
  
}