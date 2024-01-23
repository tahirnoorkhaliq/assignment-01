/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-admin-custom',
	(A) => {
		const Lang = A.Lang;

		const IN_PROGRESS_SELECTOR = '.background-task-status-in-progress';

		const INTERVAL_RENDER_IDLE = 60000;

		const INTERVAL_RENDER_IN_PROGRESS = 2000;

		const MAP_DATA_PARAMS = {
			classname: 'className',
		};

		const STR_CLICK = 'click';

		const STR_FORM = 'form';

		const STR_INDEX_ACTIONS_PANEL = 'indexActionsPanel';

		const STR_URL = 'url';

		const Admin = A.Component.create({
			ATTRS: {
				controlMenuCategoryKey: {
					validator: Lang.isString,
					value: 'tools',
				},

				form: {
					setter: A.one,
					value: null,
				},

				indexActionWrapperSelector: {
					validator: Lang.isString,
					value: null,
				},

				indexActionsPanel: {
					validator: Lang.isString,
					value: null,
				},

				redirectUrl: {
					validator: Lang.isString,
					value: null,
				},

				submitButton: {
					validator: Lang.isString,
					value: null,
				},

				url: {
					value: null,
				},
			},

			AUGMENTS: [Liferay.PortletBase],

			EXTENDS: A.Base,

			NAME: 'admin',

			prototype: {
				_addInputsFromData(data) {
					const instance = this;

					const form = instance.get(STR_FORM);

					// eslint-disable-next-line @liferay/aui/no-object
					const inputsArray = A.Object.map(data, (value, key) => {
						key = MAP_DATA_PARAMS[key] || key;

						const nsKey = instance.ns(key);

						return (
							'<input id="' +
							nsKey +
							'" name="' +
							nsKey +
							'" type="hidden" value="' +
							value +
							'" />'
						);
					});

					form.append(inputsArray.join(''));
				},

				_getControlMenuReloadItem(element) {
					let controlMenuReloadItem;

					if (!element) {
						return;
					}

					element
						.querySelectorAll('.control-menu-nav-item')
						.forEach((element) => {
							if (
								element.getElementsByClassName(
									'lexicon-icon-reload'
								).length
							) {
								controlMenuReloadItem = element;
							}
						});

					return controlMenuReloadItem;
				},

				_isBackgroundTaskInProgress() {
					const instance = this;

					const indexActionsNode = A.one(
						instance.get(STR_INDEX_ACTIONS_PANEL)
					);

					return !!(
						indexActionsNode &&
						indexActionsNode.one(IN_PROGRESS_SELECTOR)
					);
				},

				_onSubmit(event) {
					const instance = this;

					const data = event.currentTarget.getData();
					const form = instance.get(STR_FORM);

					const redirect = instance.one('#redirect', form);

					if (redirect) {
						redirect.val(instance.get('redirectUrl'));
					}

					instance._addInputsFromData(data);

					const companyIds =12345;
					

					if (!companyIds) {
						this._showError(
							Liferay.Language.get('missing-instance-error')
						);

						return;
					}

					submitForm(form, instance.get(STR_URL));
				},

				_showError(message) {
					Liferay.Util.openToast({
						message,
						type: 'danger',
					});
				},

				_updateIndexActions() {
					const instance = this;

					const currentAdminIndexPanel = A.one(
						instance.get(STR_INDEX_ACTIONS_PANEL)
					);
					console.log("currentAdminIndexPanel "+currentAdminIndexPanel);
					if (currentAdminIndexPanel) {
						Liferay.Util.fetch(instance.get(STR_URL), {
							method: 'POST',
						})
							.then((response) => {
								console.log("response1 "+response);
								return response.text();
							})
							.then((response) => {
								console.log("response2 "+response);
								const responseDataNode = A.Node.create(
									response
								);

								// Replace each progress bar

								const responseAdminIndexPanel = responseDataNode.one(
									instance.get(STR_INDEX_ACTIONS_PANEL)
								);
console.log("responseAdminIndexPanel "+responseAdminIndexPanel);
console.log("currentAdminIndexPanel "+currentAdminIndexPanel);
								if (
									currentAdminIndexPanel &&
									responseAdminIndexPanel
								) {
									const responseAdminIndexNodeList = responseAdminIndexPanel.all(
										instance.get(
											'indexActionWrapperSelector'
										)
									);
console.log(">> "+instance.get('indexActionWrapperSelector'));
									const currentAdminIndexNodeList = currentAdminIndexPanel.all(
										instance.get(
											'indexActionWrapperSelector'
										)
									);
									console.log("currentAdminIndexNodeList "+currentAdminIndexNodeList);
									console.log("responseAdminIndexNodeList "+responseAdminIndexNodeList);
									currentAdminIndexNodeList.each(
										(currentNode, index) => {
											console.log("currentNode"+currentNode)
											const responseAdminIndexNode = responseAdminIndexNodeList.item(
												index
											);

											/*const inProgress =
												currentNode.one(
													IN_PROGRESS_SELECTOR
												) ||
												responseAdminIndexNode.one(
													IN_PROGRESS_SELECTOR
												);*/

											//if (inProgress) {
												currentNode.replace(
													responseAdminIndexNode
												);
											//}
										}
									);
								}

								// Add or remove the reload icon in the top
								// control menu bar

								const responseDocument = new DOMParser().parseFromString(
									response,
									'text/html'
								);

								const controlMenuId = instance.ns(
									'controlMenu'
								);
								const controlMenuCategoryClassName = `${instance.get(
									'controlMenuCategoryKey'
								)}-control-group`;

								const currentControlMenu = document.getElementById(
									controlMenuId
								);
								const responseControlMenu = responseDocument.getElementById(
									controlMenuId
								);

								if (currentControlMenu && responseControlMenu) {
									const currentControlMenuCategory = currentControlMenu.getElementsByClassName(
										controlMenuCategoryClassName
									)[0];

									const currentReloadItem = instance._getControlMenuReloadItem(
										currentControlMenuCategory
									);

									const responseControlMenuCategory = responseControlMenu.getElementsByClassName(
										controlMenuCategoryClassName
									)[0];

									const responseReloadItem = instance._getControlMenuReloadItem(
										responseControlMenuCategory
									);

									if (
										!currentReloadItem &&
										responseReloadItem
									) {
										currentControlMenuCategory.appendChild(
											responseReloadItem
										);
									}
									else if (
										currentReloadItem &&
										!responseReloadItem
									) {
										currentReloadItem.remove();
									}
								}

								// Start timeout for refreshing the data

								let renderInterval = INTERVAL_RENDER_IDLE;

								if (instance._isBackgroundTaskInProgress()) {
									renderInterval = INTERVAL_RENDER_IN_PROGRESS;
								}

								instance._laterTimeout = A.later(
									renderInterval,
									instance,
									'_updateIndexActions'
								);
							});
					}
				},

				bindUI() {
					const instance = this;

					instance._eventHandles.push(
						instance
							.get(STR_FORM)
							.delegate(
								STR_CLICK,
								A.bind('_onSubmit', instance),
								instance.get('submitButton')
							)
					);
				},

				destructor() {
					const instance = this;

					A.Array.invoke(instance._eventHandles, 'detach');

					instance._eventHandles = null;

					A.clearTimeout(instance._laterTimeout);
				},

				initializer() {
					const instance = this;

					instance._eventHandles = [];

					instance.bindUI();

					instance._laterTimeout = A.later(
						INTERVAL_RENDER_IN_PROGRESS,
						instance,
						'_updateIndexActions'
					);
				},
			},
		});

		Liferay.Portlet.Admin = Admin;
	},
	'',
	{
		requires: [
			'aui-io-plugin-deprecated',
			'liferay-portlet-base',
			'querystring-parse',
		],
	}
);
