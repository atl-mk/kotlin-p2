declare module 'wrm/i18n' {
  export namespace I18n {
    export function getText(key: string, ...args: Array<unknown>): string;
  }
}
