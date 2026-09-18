const { spawn } = require('child_process');
const path = require('path');

const backendDir = path.resolve(__dirname, '..', 'apps', 'backend');
const isWindows = process.platform === 'win32';
const command = isWindows ? 'mvnw.cmd' : './mvnw';

const child = spawn(command, process.argv.slice(2), {
  cwd: backendDir,
  stdio: 'inherit',
  shell: isWindows
});

child.on('exit', (code) => process.exit(code ?? 1));
