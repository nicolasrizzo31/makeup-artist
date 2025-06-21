pipeline {
    agent any

    environment {
        VERSION = ""
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                sshagent (credentials: ['github-ssh']) {
                    sh 'rm -rf *'
                    sh 'git clone git@github.com:nicolasrizzo31/makeup-artist.git .'
                }
            }
        }

        stage('Calcola Versione') {
            steps {
                script {
                    def lastTag = sh(script: "git describe --tags --abbrev=0 || echo 1.0.0", returnStdout: true).trim()
                    def (major, minor, patch) = lastTag.tokenize('.')
                    def newPatch = (patch as Integer) + 1
                    VERSION = "${major}.${minor}.${newPatch}"
                    echo "➡ Nuova versione: v${VERSION}"
                }
            }
        }

        stage('Build Backend') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build -- --configuration production'
                }
            }
        }

        stage('Tag & Push') {
            steps {
                sshagent (credentials: ['github-ssh']) {
                    sh """
                    git config user.email "nicolas.rizzo31@gmail.com"
                    git config user.name "nicolas.rizzo"
                    git tag -a v${VERSION} -m "Release v${VERSION}"
                    git push origin v${VERSION}
                    """
                }
            }
        }
    }
}
