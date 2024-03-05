const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'budgetbuddy': './src/main/js/budgetbuddy.ts',
    'login': './src/main/js/login.ts',
    'logout': './src/main/js/logout.ts',
    'budgetbuddy-theme': './src/main/js/budgetbuddy-theme.less',
    'budgetbuddy-theme-dark': './src/main/js/budgetbuddy-theme-dark.less'
  };

  return config;
};
