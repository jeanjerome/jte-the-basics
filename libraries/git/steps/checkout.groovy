void call(){

  podTemplate(containers: [
      containerTemplate(
          name: 'alpine', 
          image: 'alpine:latest', 
          command: 'sleep', 
          args: '30d'
          )
    ]) {

      node(POD_LABEL) {
        container('alpine') {
          stage('checkout') {
            sh '''
            git checkout ${branch}
            '''
          }
        }
      }
      
    }
}