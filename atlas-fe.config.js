const path = require('path');

const FRONTEND_DIR = path.join(__dirname, 'src', 'frontend');
const RESOURCES_DIR = path.join(__dirname, 'src', 'main', 'resources');
const ENTRY_POINTS = {
  'atl.general': path.join(FRONTEND_DIR, 'entrypoints', 'atl-general.js'),
};

module.exports = {
  /**
    The plugin key, usually located in pom.xml file.
  */
  pluginKey: 'com.atlassian.pedagogical.kotlin-p2',

  /**
    Entry point files picked up by webpack.
  */
  entries: ENTRY_POINTS,

  /**
    atlas-fe CLI plugins to use.
  */
  plugins: [
		"@atlassian/atlas-fe-typescript",
		"@atlassian/atlas-fe-react",
		"@atlassian/atlas-fe-clientside-extensions"
],

  /**
    Atlassian translation files (*.properties) used by i18n-properties-loader
    https://www.npmjs.com/package/@atlassian/i18n-properties-loader
  */
  i18nFiles: [path.join(RESOURCES_DIR, 'kotlin-p2.properties')],

  /**
    Change the output directory used by webpack
  */
  /**
  output: path.join(__dirname, 'target', 'classes')
  */

  /**
    An object of provided dependencies consumed by WRM webpack plugin. To learn the format required to specify the dependencies,
    read WRM webpack plugin documentation: https://bitbucket.org/atlassianlabs/atlassian-webresource-webpack-plugin/src/master/#markdown-header-provideddependencies-optional
  */
  /**
  providedDependencies: {},
  */

  /**
    A function used to modify the internal webpack configuration used by atlas-fe cli.
    Use it to add your own loaders, plugins and rules, or to modify existing ones.

    The object received is a chainable webpack configuration created using webpack-chain. To learn more about its API,
    read the webpack-chain documentation: https://github.com/neutrinojs/webpack-chain
  */
  /*
  chainWebpackConfig: (webpackConfig) => {}
  */
};
