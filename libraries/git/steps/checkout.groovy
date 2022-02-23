void call(){

  podTemplate(containers: [
      containerTemplate(
          name: 'alpine-git', 
          image: 'alpine/git:latest', 
          command: 'sleep', 
          args: '30d'
          )
    ]) {

      node(POD_LABEL) {
        container('alpine-git') {
          stage('checkout') {
            sh '''
            git checkout ${branch}
            '''
          }
        }
      }
      
    }
}