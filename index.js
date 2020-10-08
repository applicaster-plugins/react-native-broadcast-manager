import { NativeModules } from 'react-native';

if (!NativeModules.BroadcastManager) { throw new Error('BroadcastManager is not registered as NativeModule')}

module.exports = NativeModules.BroadcastManager;