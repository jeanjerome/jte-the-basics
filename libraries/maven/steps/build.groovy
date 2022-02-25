void call(){

    podTemplate(
      cloud: 'kubernetes-cloud', 
      inheritFrom: 'default-agent', 
      name: 'maven-agent', 
      namespace: 'jenkins', 
      serviceAccount: 'jenkins-admin',
      volumes: [hostPathVolume(hostPath: '/Users/jeanjerome/PROJETS/jenkins-template-engine/jenkins-workspaces', mountPath: '/home/jenkins/agent')],
      containers: [
        containerTemplate(
          args: '', 
          command: ' ',
          image: 'maven:openjdk', 
          name: 'maven'
        )
      ], 
    )
    
    {
      node(POD_LABEL) {
        container('maven-agent') {
          stage('maven build') {
            sh '''
            echo "BUILD_NUMBER" :: $BUILD_NUMBER
            echo "BUILD_ID" :: $BUILD_ID
            echo "BUILD_DISPLAY_NAME" :: $BUILD_DISPLAY_NAME
            echo "JOB_NAME" :: $JOB_NAME
            echo "JOB_BASE_NAME" :: $JOB_BASE_NAME
            echo "BUILD_TAG" :: $BUILD_TAG
            echo "EXECUTOR_NUMBER" :: $EXECUTOR_NUMBER
            echo "NODE_NAME" :: $NODE_NAME
            echo "NODE_LABELS" :: $NODE_LABELS
            echo "WORKSPACE" :: $WORKSPACE
            echo "JENKINS_HOME" :: $JENKINS_HOME
            echo "JENKINS_URL" :: $JENKINS_URL
            echo "BUILD_URL" ::$BUILD_URL
            echo "JOB_URL" :: $JOB_URL
            mvn -v
            '''
          }
        }
      }
    }
    
}

