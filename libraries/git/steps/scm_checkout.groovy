void call(){

  podTemplate(containers: [
      containerTemplate(
          name: 'jenkins', 
          image: 'jenkins/inbound-agent:latest', 
          command: 'sleep', 
          args: '30d'
          )
    ]) {

      node(POD_LABEL) {
        container('jenkins') {
          stage('checkout') {
            deleteDir()
            def checkout = checkout(scm)
            unstash includes: '*', name: 'checkout'
          }
        }
      }
      
    }
}